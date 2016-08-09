package org.jasig.cas.cg;

import org.jasig.cas.web.flow.AbstractCasWebflowConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.webflow.engine.ActionState;
import org.springframework.webflow.engine.Flow;

/**
 * Created by ishara on 8/9/2016.
 */
@Component("cgTokenWebflowConfigurer")
public class CGTokenWebflowConfigurer extends AbstractCasWebflowConfigurer
{
    @Override
    protected void doInitialize() throws Exception
    {

        final Flow flow = getLoginFlow();
        final ActionState actionState = this.createActionState( flow, "cgTokenAuthenticationCheck",
                createEvaluateAction( "cgTokenAuthenticationAction" ) );
        actionState.getTransitionSet().add( createTransition( TRANSITION_ID_SUCCESS, TRANSITION_ID_SEND_TICKET_GRANTING_TICKET ) );
        createStateDefaultTransition( actionState, getStartState( flow ).getId() );
        setStartState( flow, actionState );
    }
}
