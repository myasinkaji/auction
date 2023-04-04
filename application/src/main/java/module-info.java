module application {
    requires domain;
    requires lombok;
    requires spring.web;

    
    exports ir.ddd.application.auction;
    exports ir.ddd.application.auction.contract;
}
