package com.kakaopay.app.front.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {

	private static final long serialVersionUID = -4102779437888299986L;
	
	private int pg;
	private int pageSize;
	private int pageGroupSize;
	private int total;
	private String link;
	private String beginLabel;
	private String endLabel;
	private String prevLabel;
	private String nextLabel;
	private String cssClassName;

	public int doStartTag() throws JspException {
		int pageTotal = 0;
		int pageGroupStart = 0;
		int pageGroupEnd = 0;
		try {
			StringBuffer sbuf = new StringBuffer();
			pageTotal = (total - 1) / pageSize;
			pageGroupStart = (int) (pg / pageGroupSize) * pageGroupSize;
			pageGroupEnd = pageGroupStart + pageGroupSize;
			if (pageGroupEnd > pageTotal) {
				pageGroupEnd = pageTotal + 1;
			}

			sbuf.append(makeLink_img(0, beginLabel));

			if (pg == 0) {
				sbuf.append(makeLink_img(pg, prevLabel));
			} else {
				sbuf.append(makeLink_img(pg - 1, prevLabel));
			}

			String class_str = "";
			for (int i = pageGroupStart; i < pageGroupEnd; i++) {
				if (i == pageGroupStart) {
					class_str = "class='liFirst'";
				} else if (i == (pageGroupEnd - 1)) {
					class_str = "class='liLast'";
				} else {
					class_str = "";
				}

				if (i == pg) {
					sbuf.append("<li><span class=\"on\"" + class_str
							+ " style='color: white; font-weight: bold; background-color: gray;'>").append(i + 1)
							.append("</span></li>");
				} else {
					sbuf.append(makeLink(i, String.valueOf((i + 1)), class_str));
				}
			}

			if (pg == pageTotal) {
				sbuf.append(makeLink_img(pg, nextLabel));
			} else {
				sbuf.append(makeLink_img(pg + 1, nextLabel));
			}

			sbuf.append(makeLink_img(pageTotal, endLabel));
			pageContext.getOut().print(sbuf);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

	private String makeLink_img(int pg, String label) {
		StringBuffer tmp = new StringBuffer();

		tmp.append("<li><a " + label + " href=\"javascript:" + link + "('" + pg + "');\" ><span>페이지</span>")
				.append("</a></li>");

		return tmp.toString();
	}

	private String makeLink(int pg, String label, String class_str) {
		StringBuffer tmp = new StringBuffer();

		tmp.append("<li onclick=\"javascript:" + link + "('" + pg + "');\"><span " + class_str + ">").append(label)
				.append("</span></li>");

		return tmp.toString();
	}

	public int getPg() {
		return pg;
	}

	public void setPg(int pg) {
		this.pg = pg;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageGroupSize() {
		return pageGroupSize;
	}

	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBeginLabel() {
		return beginLabel;
	}

	public void setBeginLabel(String beginLabel) {
		this.beginLabel = beginLabel;
	}

	public String getEndLabel() {
		return endLabel;
	}

	public void setEndLabel(String endLabel) {
		this.endLabel = endLabel;
	}

	public String getPrevLabel() {
		return prevLabel;
	}

	public void setPrevLabel(String prevLabel) {
		this.prevLabel = prevLabel;
	}

	public String getNextLabel() {
		return nextLabel;
	}

	public void setNextLabel(String nextLabel) {
		this.nextLabel = nextLabel;
	}

	public String getCssClassName() {
		return cssClassName;
	}

	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}

}