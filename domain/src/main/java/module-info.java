module domain {
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.mongodb;
    requires lombok;

    exports ir.ddd.domain.auction;
    opens ir.ddd.domain.auction to spring.core;
}
