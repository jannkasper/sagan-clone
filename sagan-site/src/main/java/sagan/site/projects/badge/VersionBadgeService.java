package sagan.site.projects.badge;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.xmlbeam.XBProjector;
import org.xmlbeam.config.DefaultXMLFactoriesConfig;
import sagan.site.projects.Project;
import sagan.site.projects.Release;
import sagan.site.projects.ReleaseStatus;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Service to generate SVG badges.
 *
 * @author Mark Paluch
 */
@Service
public class VersionBadgeService {

    private final URL PRERELEASE_TEMPLATE = getClass().getResource("/badge/milestone.svg");
    private final URL GENERAL_AVAILABILITY_TEMPLATE = getClass().getResource("/badge/release.svg");
    private final URL SNAPSHOT_TEMPLATE = getClass().getResource("/badge/snapshot.svg");
    private final URL VERDANA_FONT = getClass().getResource("/badge/Verdana.ttf");
    private final BufferedImage DUMMY = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    private Graphics graphics;
    private XBProjector xbProjector;

    @PostConstruct
    public void postConstruct() throws Exception {

        Font font;
        try (InputStream is = VERDANA_FONT.openStream()) {
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(0, 11);
        }

        graphics = DUMMY.getGraphics();
        graphics.setFont(font);

        DefaultXMLFactoriesConfig myConfig = new DefaultXMLFactoriesConfig();
        myConfig.setNamespacePhilosophy(DefaultXMLFactoriesConfig.NamespacePhilosophy.NIHILISTIC);

        xbProjector = new XBProjector(myConfig, XBProjector.Flags.TO_STRING_RENDERS_XML);
    }

    @PreDestroy
    public void preDestroy() {
        graphics.dispose();
    }

    /**
     * Create a version badge for a given {@link Project} and {@link Release}. The badge uses SVG and is returned as byte
     * array.
     *
     * @param project must not be {@literal null}.
     * @param release must not be {@literal null}.
     * @return
     * @throws IOException
     */
    public byte[] createSvgBadge(Project project, Release release) throws IOException {

        Assert.notNull(project, "Project must not be null!");
        Assert.notNull(release, "ProjectRelease must not be null!");

        URL template = getTemplate(release.getReleaseStatus());

        BadgeSvg svgDocument = xbProjector.io().url(template.toString()).read(BadgeSvg.class);

        List<BadgeSvg.Path> paths = svgDocument.getPaths();

        String label = project.getName();
        String version = release.getVersion().toString();

        return createSvgBadge(svgDocument, paths, label, version);
    }

    private byte[] createSvgBadge(BadgeSvg svgDocument, List<BadgeSvg.Path> paths, String label, String version) {

        List<BadgeSvg.GraphicElement> graphicElements = svgDocument.getGraphicElements();
        graphicElements.get(0).setText(label);
        graphicElements.get(1).setText(label);

        graphicElements.get(2).setText(version);
        graphicElements.get(3).setText(version);

        int labelMarginLeft = 6;
        int labelMarginRight = 4;

        int versionMarginLeft = 4;
        int versionMarginRight = 6;

        int labelTextWidth;
        int versionTextWidth;

        synchronized (graphics) {
            FontMetrics fontMetrics = graphics.getFontMetrics();
            labelTextWidth = fontMetrics.stringWidth(label);
            versionTextWidth = fontMetrics.stringWidth(version);
        }

        int labelWidth = labelTextWidth + labelMarginLeft + labelMarginRight;
        int versionWidth = versionTextWidth + versionMarginLeft + versionMarginRight;

        svgDocument.setWidth(labelWidth + versionWidth);
        svgDocument.getMask().setWidth(labelWidth + versionWidth);

        graphicElements.get(0).setXPosition(labelMarginLeft + (labelTextWidth / 2));
        graphicElements.get(1).setXPosition(labelMarginLeft + (labelTextWidth / 2));

        graphicElements.get(2).setXPosition(labelWidth + versionMarginLeft + (versionTextWidth / 2));
        graphicElements.get(3).setXPosition(labelWidth + versionMarginLeft + (versionTextWidth / 2));

        paths.get(0).setDraw(String.format("M0 0h%dv20H0z", labelWidth));
        paths.get(1).setDraw(String.format("M%d 0h%dv20H%dz", labelWidth, versionWidth, labelWidth));
        paths.get(2).setDraw(String.format("M0 0h%dv20H0z", labelWidth + versionWidth));

        return svgDocument.toString().getBytes();
    }

    private URL getTemplate(ReleaseStatus releaseStatus) {

        switch (releaseStatus) {
            case GENERAL_AVAILABILITY:
                return GENERAL_AVAILABILITY_TEMPLATE;
            case PRERELEASE:
                return PRERELEASE_TEMPLATE;
            default:
                return SNAPSHOT_TEMPLATE;
        }
    }
}
