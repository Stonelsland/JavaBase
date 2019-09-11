package com.lyx.java.Network;

/**
 * 接口的应用:代理模式
 */
public class NetWorkTest {
    public static void main(String[] args) {
        Server server=new Server();
        ProxyServer proxyServer = new ProxyServer(server);
        proxyServer.browse();
    }
}

interface NetWork {
    public void browse();
}

//被代理类
class Server implements NetWork {

    @Override
    public void browse() {
        System.out.println("服务器访问网络");
    }
}

//代理类
class ProxyServer implements NetWork {
    private NetWork netWork;

    public ProxyServer(NetWork netWork) {
        this.netWork = netWork;
    }

    public void check(){
        System.out.println("检查网络连接");
    }
    @Override
    public void browse() {
        check();
        netWork.browse();
    }
}