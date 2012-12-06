package cse.uta.edu.os2.client;


import java.io.ByteArrayOutputStream;
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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class is responsible for calling the web service and getting the response from the web service
 * It has three methods, first method calls the web service and gets the output
 * and other two methods gets the XML request and response
 * @author lakshmana s
 *
 */
public class SpellCheckClient {

	//String xml request and response
	String requestXMl=null,responseXML=null;
	//list of suggested words
	ArrayList<String> suggestedWords=new ArrayList<String>();		
	SOAPMessage request=null,response=null;
	
	/**
	 * This method takes misspelled word, and calls the webservice and gets the response
	 * it returns the list of suggested words
	 * @param inputWord
	 * @return List of suggested word
	 */
	public ArrayList<String> callService(String inputWord){
		try {
			suggestedWords.clear();
			//creates Message Facotry
			MessageFactory requestFac = MessageFactory.newInstance();
			//creates Soap Message
			request= requestFac.createMessage();
			MimeHeaders mimeHeader =request.getMimeHeaders();
			mimeHeader.addHeader("soapAction", "http://ws.cdyne.com/CheckTextBodyV2");
			SOAPBody soapBody =request.getSOAPBody();
			
			//fills in the SOAP Body with request paramters
			QName txtBody = new QName("http://ws.cdyne.com/","CheckTextBodyV2");
			SOAPElement soapElem1 =soapBody.addBodyElement(txtBody);
			QName bodyTxt = new QName("http://ws.cdyne.com/","BodyText");
			SOAPElement soapElem2 =soapElem1.addChildElement(bodyTxt);

			//sets the misspelled word
			soapElem2.addTextNode(inputWord);
			
			//creates the connection factory to call the webservice
			SOAPConnectionFactory conFac = SOAPConnectionFactory.newInstance();
			//gets the connection object from factory 
			SOAPConnection connection = conFac.createConnection();
			URL endpoint = new URL("http://wsf.cdyne.com/SpellChecker/check.asmx");
			
			//gets the XML request from SOAP Message
			requestXMl=getRequestXML();
			System.out.println("Request :\n "+requestXMl);
		
			//calls the webservice with the request and endpoint
			response =connection.call(request, endpoint);
			
			if(response!=null){
				//gets the soap body from response
				SOAPBody respSoapBody =response.getSOAPBody();
				if(respSoapBody!=null){
					//gets the response XML string 
					responseXML=getResponseXML();
					System.out.println("Response :\n "+responseXML);
					
					//gets the SuggestionCount tag from the response
					NodeList nList= respSoapBody.getElementsByTagName("SuggestionCount");
					if(nList!=null && nList.getLength()>0){
						Node countNode =nList.item(0);
						String sCount=null;
						//get the Suggestion word count from response
						if(countNode!=null){
							sCount=countNode.getTextContent();
							System.out.println("Suggestion Word Count :"+sCount);
						}
						//if suggestion word count > 0 , parse the xml response
						if(sCount!=null){
							int count= new Integer(sCount);
							if(count>0){
								//gets the list of MisSpelled word 
								NodeList nodeList=  respSoapBody.getElementsByTagName("MisspelledWord");
								Node suggNode = nodeList.item(0);
								NodeList suggList =suggNode.getChildNodes();
								//prints the misspelled word 
								for (int i=0;i<count;i++){
									Node node =suggList.item(i);
									if(node.getNodeType()==Node.ELEMENT_NODE){
										String word =node.getTextContent();
										System.out.println(i+" : "+word);
										//adds the misspelled to the list
										suggestedWords.add(word);
									}
								}
							}
						}
					}
				}
			}
			connection.close();
		} 
		catch (SOAPException e) {
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		//returns the list of suggested words 
		return suggestedWords;
		
	}
	
	/**
	 * this method gets the string format of the xml request from the soap message
	 * @return XML Request
	 */
	public String getRequestXML(){
		try {
			//if request is not null && get the soap part and content
			if(request!=null && request.getSOAPPart()!=null && request.getSOAPPart().getContent()!=null){
				Source source=request.getSOAPPart().getContent();
				//creates the transform object to transform the soap object to xml format
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transf = tf.newTransformer();
				//this displays the pretty xml string
				transf.setOutputProperty(OutputKeys.INDENT, "yes");
				transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				StreamResult result = new StreamResult(byteOut);
				transf.transform(source, result);
				//gets the string from transformed byte object
				requestXMl = byteOut.toString();
			}
			else
				//if object is null
				requestXMl= "Input xml creation failed";
		}
		catch (SOAPException e) {
			e.printStackTrace();
		}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		} 
		return requestXMl;
	}

	

	/**
	 * this method gets the string format of the xml request from the soap message
	 * @return XML Response
	 */
	public String getResponseXML(){
		try {
			//if request is not null && get the soap part and content
			if(response.getSOAPPart()!=null && response.getSOAPPart().getContent()!=null){
				Source source=response.getSOAPPart().getContent();
				//creates the transform object to transform the soap object to xml format
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transf = tf.newTransformer();
				//this displays the pretty xml string
				transf.setOutputProperty(OutputKeys.INDENT, "yes");
				transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				StreamResult result = new StreamResult(byteOut);
				transf.transform(source, result);
				//gets the string from transformed byte object
				responseXML = byteOut.toString();
				}
			else 
				//if object is null
				responseXML="No Suggestion for the entered word, try misspelled word";
		}
		catch (SOAPException e) {
			e.printStackTrace();
		}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		} 
		return responseXML;
	}

	
	public static void main(String[] args) {
		SpellCheckClient client =new SpellCheckClient();
		//client.getSuggestionWord("resf");
		client.callService("resp");
	}

}
