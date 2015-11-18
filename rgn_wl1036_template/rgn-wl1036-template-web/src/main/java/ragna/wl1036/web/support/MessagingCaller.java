/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragna.wl1036.web.support;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Desabilitado por enquanto para deploy em dev.
 * 
 * @author npadilha
 */
//@Controller
public class MessagingCaller {
    
    //@Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(value = "/call_test_topic")
    public @ResponseBody
    String versionMap() {

        final MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("ping!");
            }

        };
        
        jmsTemplate.send("jms/TestQ", messageCreator);
        
        return "sent!";
    }
}
