package pe.edu.idat.pedidos.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.connection.CachingConnectionFactory;


import org.springframework.jms.support.converter.SimpleMessageConverter;

@Configuration
@EnableJms
public class JmsConfig {

    // Configuración de la conexión de ActiveMQ
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");  // Asegúrate de que tu broker esté en este puerto
        return factory;
    }

    // Configuración de la conexión con caché
    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(activeMQConnectionFactory());
    }

    // Configuración del JmsTemplate con un MessageConverter
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());  // Convertir objetos automáticamente
        return jmsTemplate;
    }

    // Definir el jmsListenerContainerFactory bean necesario para los listeners JMS
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory());
        factory.setConcurrency("1-1");  // Ajusta según tus necesidades
        return factory;
    }
}