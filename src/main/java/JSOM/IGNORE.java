package JSOM;

	import java.io.IOException;

import javax.xml.ws.Response;

	import net.sf.json.JSONObject;

	import org.codehaus.jackson.JsonGenerationException;
	import org.codehaus.jackson.annotate.JsonIgnore;
	import org.codehaus.jackson.annotate.JsonProperty;
	import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
//�㲻������ʲô��˼������������
	public class IGNORE {
		
		@JsonProperty("ActionCode")
		private String ActionCode = "";
		
		@JsonProperty("TransactionID")
		private String TransactionID = "";
		
		@JsonProperty("RspTime")
		private String RspTime = "";
		
		@JsonProperty("DigitalSign")
		private String DigitalSign = "";
		
		@JsonProperty("Response")
		private Response Response; 
		
		public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
	IGNORE sh = new IGNORE();
	sh.setActionCode("1");
		
			ObjectMapper mapper = new ObjectMapper(); 
			String json=mapper.writeValueAsString(sh);
			System.out.println(json); 
			
			sh=mapper.readValue(json, IGNORE.class);
			System.out.println(sh.getActionCode());
		}
		
		@JsonIgnore
		public String getActionCode() {
			return ActionCode;
		}

		@JsonIgnore
		public void setActionCode(String actionCode) {
			ActionCode = actionCode;
		}

		@JsonIgnore
		public String getTransactionID() {
			return TransactionID;
		}

		@JsonIgnore
		public void setTransactionID(String transactionID) {
			TransactionID = transactionID;
		}

		@JsonIgnore
		public String getRspTime() {
			return RspTime;
		}

		@JsonIgnore
		public void setRspTime(String rspTime) {
			RspTime = rspTime;
		}

		@JsonIgnore
		public String getDigitalSign() {
			return DigitalSign;
		}

		@JsonIgnore
		public void setDigitalSign(String digitalSign) {
			DigitalSign = digitalSign;
		}

		@JsonIgnore
		public Response getResponse() {
			return Response;
		}

		@JsonIgnore
		public void setResponse(Response response) {
			Response = response;
		}
	}



