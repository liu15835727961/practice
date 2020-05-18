package com.git.webservice;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;

/**
 * Webservice接口方法
 *
 * @author yulj
 * @create: 2019/03/12 19:50
 */
@Component
public class NameService {

    public String getName(String name) {
        return "你的名字是"+name;
    }


    public static void main(String[] args) throws AxisFault {
        testDocument();
    }
    /**
     * 方法一：
     * 应用rpc的方式调用 这种方式就等于远程调用，
     * 即通过url定位告诉远程服务器，告知方法名称，参数等， 调用远程服务，得到结果。
     * 使用 org.apache.axis2.rpc.client.RPCServiceClient类调用WebService
     *
     【注】：

     如果被调用的WebService方法有返回值 应使用 invokeBlocking 方法 该方法有三个参数
     第一个参数的类型是QName对象，表示要调用的方法名；
     第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
     当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。
     第三个参数表示WebService方法的 返回值类型的Class对象，参数类型为Class[]。


     如果被调用的WebService方法没有返回值 应使用 invokeRobust 方法
     该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同。

     在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名，
     也就是 <wsdl:definitions>元素的targetNamespace属性值。
     *
     */
    private static void rpctest() throws AxisFault {
        String url = "http://localhost/services/nameService?wsdl";
        // 使用RPC方式调用WebService
        RPCServiceClient serviceClient = new RPCServiceClient();
        // 指定调用WebService的URL
        EndpointReference targetEPR = new EndpointReference(url);
        Options options = serviceClient.getOptions();
        //确定目标服务地址
        options.setTo(targetEPR);
        //确定调用方法
        options.setAction("urn:getName");
        /**
         * 指定要调用的getPrice方法及WSDL文件的命名空间
         * 如果 webservice 服务端由axis2编写
         * 命名空间 不一致导致的问题
         * org.apache.axis2.AxisFault: java.lang.RuntimeException: Unexpected subelement arg0
         */
        QName qname = new QName("http://webservice.git.com", "getName");
        // 指定getPrice方法的参数值
        Object[] parameters = new Object[] { "13" };
        // 指定getPrice方法返回值的数据类型的Class对象
        Class[] returnTypes = new Class[] { String.class };

        // 调用方法一 传递参数，调用服务，获取服务返回结果集
        OMElement element = serviceClient.invokeBlocking(qname, parameters);
        //值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
        //我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
        String result = element.getFirstElement().getText();
        System.out.println(result);
        // 调用方法二 getPrice方法并输出该方法的返回值
        Object[] response = serviceClient.invokeBlocking(qname, parameters, returnTypes);
        // String r = (String) response[0];
        String r = (String) response[0];
        System.out.println(r);
    }
    /**
     * 方法二： 应用document方式调用
     * 用ducument方式应用现对繁琐而灵活。现在用的比较多。因为真正摆脱了我们不想要的耦合
     */
    public static void testDocument() throws AxisFault {
            String url = "http://localhost/services/nameService?wsdl";
            Options options = new Options();
            // 指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(url);
            options.setTo(targetEPR);
                 ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            OMFactory fac = OMAbstractFactory.getOMFactory();
            String tns = "http://webservice.git.com";
            // 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的
            OMNamespace omNs = fac.createOMNamespace(tns, "");
            //getName调用方法名
            OMElement method = fac.createOMElement("getName", omNs);
            //name:getName方法的参数名称
            OMElement symbol = fac.createOMElement("name", omNs);
            symbol.addChild(fac.createOMText(symbol, "Axis2 Echo String "));
            method.addChild(symbol);
            method.build();
            OMElement result = sender.sendReceive(method);
            System.out.println(result);
    }
    /**
     * 为SOAP Header构造验证信息，
     * 如果你的服务端是没有验证的，那么你不用在Header中增加验证信息
     */
    public void addValidation( String user, String passwrod) throws AxisFault {
        String url = "http://localhost/services/nameService?wsdl";
        // 使用RPC方式调用WebService
        RPCServiceClient serviceClient = new RPCServiceClient();
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(url, "nsl");
        OMElement header = fac.createOMElement("AuthenticationToken", omNs);
        OMElement ome_user = fac.createOMElement("Username", omNs);
        OMElement ome_pass = fac.createOMElement("Password", omNs);
        ome_user.setText(user);
        ome_pass.setText(passwrod);
        header.addChild(ome_user);
        header.addChild(ome_pass);
        serviceClient.addHeader(header);
    }
}