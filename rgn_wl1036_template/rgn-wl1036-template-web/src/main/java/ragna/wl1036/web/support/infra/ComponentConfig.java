/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragna.wl1036.web.support.infra;

import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

/**
 * http://www.stefanalexandru.com/java/spring-4-jms-connection-with-java-config-and-weblogic-as-jms-provider
 * https://blogs.oracle.com/soaproactive/entry/how_to_create_a_simple
 *
 * @author npadilha
 */
// desabilitado para deploy em dev. sera retomada a configuracao
//@Configuration
//@EnableJms
//@PropertySource({"classpath:/messaging/wl-jndi.properties"})
public class ComponentConfig {

    @Autowired
    private Environment env;

    @Bean
    public JndiTemplate jndiTemplate() {
        Properties jndiProps = new Properties();

        jndiProps.setProperty("java.naming.factory.initial", env.getProperty("jms.jndi.factory.initial"));
        jndiProps.setProperty("java.naming.provider.url", env.getProperty("jms.provider.url"));
        jndiProps.setProperty("java.naming.security.principal", env.getProperty("jms.user"));
        jndiProps.setProperty("java.naming.security.credentials", env.getProperty("jms.pwd"));

        JndiTemplate jndiTemplate = new JndiTemplate(jndiProps);

        return jndiTemplate;
    }
    
    @Bean
    public JndiObjectFactoryBean jmsConnectionFactory() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/QCF"); // connectionFactory name.

        return jndiObjectFactoryBean;
    }

    @Bean
    public JndiObjectFactoryBean jmsQueueName() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/TestQ"); //queue name

        return jndiObjectFactoryBean;
    }
    
    @Bean
    public JmsTemplate jmsSenderTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate((ConnectionFactory) jmsConnectionFactory().getObject());


        jmsTemplate.setSessionTransacted(false);
        jmsTemplate.setReceiveTimeout(5000);
        jmsTemplate.setDefaultDestination((Destination) jmsQueueName().getObject());

        return jmsTemplate;
    }
}
