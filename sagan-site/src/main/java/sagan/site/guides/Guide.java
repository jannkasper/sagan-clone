package sagan.site.guides;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface Guide extends GuideHeader {

	String getContent();

	String getTableOfContents();

}
