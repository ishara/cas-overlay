package org.jasig.cas.cg;

import org.jasig.cas.authentication.BasicIdentifiableCredential;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.integration.pac4j.authentication.handler.support.AbstractWrapperAuthenticationHandler;
import org.pac4j.core.credentials.Credentials;
import org.pac4j.http.credentials.TokenCredentials;
import org.pac4j.http.credentials.authenticator.Authenticator;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;

/**
 * Created by ishara on 8/5/2016.
 */
@Component("cgTokenAuthenticationHandler")
public class CGTokenAuthenticationHandler extends AbstractWrapperAuthenticationHandler
{
    @Override protected Credentials convertToPac4jCredentials( Credential casCredential ) throws GeneralSecurityException, PreventedException
    {
        this.logger.debug( "CAS credentials: {}", casCredential );
        TokenCredentials credentials = new TokenCredentials( casCredential.getId(), this.getClass().getSimpleName() );
        this.logger.debug( "pac4j credentials: {}", credentials );
        return credentials;
    }

    @Override protected Class getCasCredentialsType()
    {
        return BasicIdentifiableCredential.class;
    }

    @Override protected Authenticator getAuthenticator( Credential credential )
    {
        return new CGAuthenticator();
    }
}
