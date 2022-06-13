package org.apache.wiki.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class AkismetSpamFilterInsertions
implements SpamFilterInsertions
{
	@Override
	public String getHTMLInsertion(PageContext pageContext)
	{
        final String encodingCheckInput = AkismetSpamFilter.insertInputFields( pageContext );
        final String hashCheckInput =
            "<input type='hidden' name='" + AkismetSpamFilter.getHashFieldName( ( HttpServletRequest ) pageContext.getRequest() ) + "'" +
            " value='" + pageContext.getAttribute( "lastchange", PageContext.REQUEST_SCOPE ) + "' />\n";

        // This following field is only for the SpamFilter to catch bots which are just randomly filling all fields and submitting.
        // Normal user should never see this field, nor type anything in it.
        
        String n = AkismetSpamFilter.getBotFieldName();
        final String botCheckInput =
            "<input class='hidden' type='text' name='" + n + "' id='" + n + "' value='' />\n";
        
        return encodingCheckInput + hashCheckInput + botCheckInput;
    }
}
