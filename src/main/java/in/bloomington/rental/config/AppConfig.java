package in.bloomington.rental.config;

import java.util.Properties;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.Zoning;
import in.bloomington.rental.model.OwnerPhone;
import in.bloomington.rental.model.PropertyType;
import in.bloomington.rental.model.PullReason;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.Variance;
import in.bloomington.rental.model.RentalOwner;
import in.bloomington.rental.model.BuildingType;
import in.bloomington.rental.model.RentalStatus;
import in.bloomington.rental.model.RentalUnit;
import in.bloomington.rental.model.RentalStructure;
import in.bloomington.rental.model.InspectionType;
import in.bloomington.rental.model.RentalNote;
import in.bloomington.rental.model.RentalLog;
import in.bloomington.rental.model.RentalLegal;
import in.bloomington.rental.model.PullHistory;
import in.bloomington.rental.model.Bill;
import in.bloomington.rental.model.Receipt;
import in.bloomington.rental.model.Inspection;
import in.bloomington.rental.model.InspectionCan;
import in.bloomington.rental.model.Can;
import in.bloomington.rental.model.Egress;
import in.bloomington.rental.model.InspectionTemplate;
import in.bloomington.rental.model.TemplateComponent;
import in.bloomington.rental.model.EmailLog;
import in.bloomington.rental.model.EmailDetailLog;
import in.bloomington.rental.model.Attachement;
import in.bloomington.rental.model.AttachementSeq;
import in.bloomington.rental.model.LegalItEmailLog;
import in.bloomington.rental.model.InspectionFileLog;
import in.bloomington.rental.model.UnitRoom;
import in.bloomington.rental.model.StandardFees;

@Configuration
@PropertySource("file:/srv/data/renttrack/conf/application.properties")

@EnableTransactionManagement
@ComponentScans(value = {
    @ComponentScan("in.bloomington.rental.model"),
    @ComponentScan("in.bloomington.rental.dao"),
    @ComponentScan("in.bloomington.rental.service"),
    @ComponentScan("in.bloomington.rental.util")
})

public class AppConfig
{
    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties              props       = new Properties();
        props.put(AvailableSettings.DRIVER,         env.getProperty("postgres.driver"));
        props.put(AvailableSettings.URL,            env.getProperty("postgres.url"));
        props.put(AvailableSettings.USER,           env.getProperty("postgres.user"));
        props.put(AvailableSettings.PASS,           env.getProperty("postgres.password"));
        props.put(AvailableSettings.DIALECT,        env.getProperty("postgres.dialect"));
        props.put(AvailableSettings.DEFAULT_SCHEMA, env.getProperty("postgres.schema"));

        props.put(AvailableSettings.SHOW_SQL,     env.getProperty("hibernate.show_sql"));
        props.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        props.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, "true");

        // Setting C3P0 pooling properties
        props.put(AvailableSettings.C3P0_MIN_SIZE,          env.getProperty("hibernate.c3p0.min_size"));
        props.put(AvailableSettings.C3P0_MAX_SIZE,          env.getProperty("hibernate.c3p0.max_size"));
        props.put(AvailableSettings.C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
        props.put(AvailableSettings.C3P0_TIMEOUT,           env.getProperty("hibernate.c3p0.timeout"));
        props.put(AvailableSettings.C3P0_MAX_STATEMENTS,    env.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(Address.class,
                                        RentUser.class,
                                        Owner.class,
                                        OwnerPhone.class,
                                        PropertyType.class,
                                        BuildingType.class,
                                        RentalStatus.class,
                                        Zoning.class,
                                        InspectionType.class,
                                        PullReason.class,
                                        Rental.class,
                                        RentalOwner.class,
                                        RentalStructure.class,
                                        RentalUnit.class,
                                        Variance.class,
                                        RentalNote.class,
                                        PullHistory.class,
                                        Bill.class,
                                        Receipt.class,
                                        AttachementSeq.class,
                                        Attachement.class,
                                        LegalItEmailLog.class,
                                        RentalLog.class,
                                        Inspection.class,
                                        Can.class,
                                        Egress.class,
                                        InspectionCan.class,
                                        EmailLog.class,
                                        RentalLegal.class,
                                        EmailDetailLog.class,
                                        InspectionFileLog.class,
                                        InspectionTemplate.class,
                                        TemplateComponent.class,
                                        StandardFees.class,
                                        UnitRoom.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager()
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver()
    {
        CommonsMultipartResolver multi = new CommonsMultipartResolver();
        multi.setMaxUploadSize(10000000);
        return multi;
    }
}
