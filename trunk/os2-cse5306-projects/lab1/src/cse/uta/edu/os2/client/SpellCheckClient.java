package cse.uta.edu.os2.client;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;








public class SpellCheckClient {

	/**
	 * @param args
	 */
	
	String requestXMl=null,responseXML=null;
	ArrayList<String> suggestedWords=new ArrayList<String>();		
	
	public ArrayList<String> callService(String inputWord){
		try {
			MessageFactory msgFac = MessageFactory.newInstance();
			SOAPMessage msg= msgFac.createMessage();
			MimeHeaders mimeHeader =msg.getMimeHeaders();
			mimeHeader.addHeader("soapAction", "http://ws.cdyne.com/CheckTextBodyV2");
			SOAPBody soapBody =msg.getSOAPBody();
			
			QName txtBody = new QName("http://ws.cdyne.com/","CheckTextBodyV2");
			SOAPElement soapElem1 =soapBody.addBodyElement(txtBody);
			QName bodyTxt = new QName("http://ws.cdyne.com/","BodyText");
			SOAPElement soapElem2 =soapElem1.addChildElement(bodyTxt);
			soapElem2.addTextNode(inputWord);
			
			
			SOAPConnectionFactory conFac = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = conFac.createConnection();
			URL endpoint = new URL("http://wsf.cdyne.com/SpellChecker/check.asmx");
			SOAPMessage response =connection.call(msg, endpoint);
			SOAPBody respSoapBody =response.getSOAPBody();
			
			requestXMl=msg.getSOAPPart().getEnvelope().toString();
			System.out.println("Request :\n "+requestXMl);
			
			responseXML=response.getSOAPPart().getEnvelope().toString();
			System.out.println("Response :\n "+responseXML);
						
			NodeList nList= respSoapBody.getElementsByTagName("SuggestionCount");
			Node countNode =nList.item(0);
			String sCount=countNode.getTextContent();
			System.out.println("Suggestion Word Count :"+sCount);
			
			NodeList nodeList=  respSoapBody.getElementsByTagName("MisspelledWord");
			Node suggNode = nodeList.item(0);
			NodeList suggList =suggNode.getChildNodes();

			int count= new Integer(sCount);
			for (int i=0;i<count;i++){
				Node node =suggList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE){
					String word =node.getTextContent();
					System.out.println(i+" : "+word);
					suggestedWords.add(word);
				}
			}
			System.out.println();
			connection.close();

			
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return suggestedWords;
		
	}
	
	
	public String getRequestXML(){
		return requestXMl;
	}

	public String getResponseXML(){
		return responseXML;
	}

	
	public static void main(String[] args) {
		SpellCheckClient client =new SpellCheckClient();
		//client.getSuggestionWord("resf");
		client.callService("helt");
	}

}
