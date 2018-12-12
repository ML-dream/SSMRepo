package Test;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;  
import java.util.Map;  
import java.util.concurrent.Executors;  
  
import org.jinterop.dcom.common.JIException;  
import org.jinterop.dcom.core.JIVariant;  
import org.openscada.opc.dcom.list.ClassDetails;
import org.openscada.opc.lib.common.ConnectionInformation;  
import org.openscada.opc.lib.da.AutoReconnectController;
import org.openscada.opc.lib.da.Group;  
import org.openscada.opc.lib.da.Item;  
import org.openscada.opc.lib.da.ItemState;  
import org.openscada.opc.lib.da.Server;  
import org.openscada.opc.lib.da.browser.Branch;  
import org.openscada.opc.lib.da.browser.Leaf;  
import org.openscada.opc.lib.list.Categories;
import org.openscada.opc.lib.list.Category;
import org.openscada.opc.lib.list.ServerList;
  


public class shujucaiji {  
    public static void main (  String[] args ) throws Throwable  
    {  
    	 String host = "localhost";  
    	 String domain = "localhost";  
    	 String progId = "Knight.OPC.Server.Demo";  
    	 String user = "opcuser";  
    	 String password = "123"; 
    	/*ServerList serverList = new ServerList(host,user,password,domain);  
    	showAllOPCServer(serverList);*/
    	final ConnectionInformation ci = new ConnectionInformation();  
    	ci.setHost(host);  
        ci.setDomain("localhost");
    	ci.setClsid("B57C679B-665D-4BB0-9848-C5F2C4A6A280");
    	  ci.setUser("Administrator");
    	   ci.setPassword("10012719"); 
    	   AutoReconnectController autos = null;
    	   final Server s = new Server(ci,Executors.newSingleThreadScheduledExecutor());
           autos = new AutoReconnectController(s);
           autos.connect();
           Thread.sleep(100);
           Group group = s.addGroup("test");
           group.setActive(true);
           final Item item = group.addItem("Channel1.Device1.D0");
           item.setActive(true);
           Thread.sleep(100);
           System.out.println("读取值："+item.read(false).getValue().getObjectAsUnsigned().getValue());
           JIVariant value = JIVariant.makeVariant(new Integer(777));
           item.write(value);
     		
    	
	
}  
    protected static void showAllOPCServer(ServerList serverList) throws IllegalArgumentException, UnknownHostException, JIException {
		 final Collection<ClassDetails> detailsList = serverList.listServersWithDetails (  
	            new Category[] { Categories.OPCDAServer20 }, new Category[] {} );  
		 for ( final ClassDetails details : detailsList )  
	    {  
	        System.out.println ( String.format ( "Found: %s", details.getClsId () ) );  
	        System.out.println ( String.format ( "\tProgID: %s", details.getProgId () ) );  
	        System.out.println ( String.format ( "\tDescription: %s", details.getDescription () ) );  
	    }  
	}  
}
