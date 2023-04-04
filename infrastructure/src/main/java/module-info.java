module infrastructure {

    requires spring.data.mongodb;
    requires spring.data.commons;
    requires spring.context;
    requires spring.boot;
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires spring.beans;
    requires spring.aop;


    requires lombok;

    requires application;
    requires domain;

    requires java.sql;
    exports ir.ddd;
    exports ir.ddd.infrastructure.auction.persistence to spring.beans, spring.aop;
    exports ir.ddd.infrastructure.auction.rest to spring.beans, spring.web;
    opens ir.ddd.infrastructure.auction.persistence to spring.core;
    opens ir.ddd;
}
