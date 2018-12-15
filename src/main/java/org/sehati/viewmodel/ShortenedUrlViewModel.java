package org.sehati.viewmodel;

import java.io.Serializable;

import org.sehati.domain.Link;
import org.sehati.util.SpringShortenedUtil;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

public class ShortenedUrlViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7395724034298583941L;

	private Link link = new Link();
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }

	@Command
	@NotifyChange({"link"})
	public void onGenerate() {
		link = SpringShortenedUtil.getShortenedUrlService().generateShortenedUrl(link);
		link.setOriginalLink(null);
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}
	
}
