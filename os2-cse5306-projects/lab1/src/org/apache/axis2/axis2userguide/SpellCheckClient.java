package org.apache.axis2.axis2userguide;

import java.rmi.RemoteException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.axis2userguide.CheckStub.CheckTextBodyV2;
import org.apache.axis2.axis2userguide.CheckStub.CheckTextBodyV2Response;
import org.apache.axis2.axis2userguide.CheckStub.DocumentSummary;
import org.apache.axis2.axis2userguide.CheckStub.Words;
import org.apache.axis2.databinding.ADBException;





public class SpellCheckClient {

	/**
	 * @param args
	 */
	CheckTextBodyV2Response response=null;
	public String[] getSuggestionWord(){
		String []sug=null;
		try {
				CheckStub stub = new CheckStub();
				
				CheckTextBodyV2 req = new CheckTextBodyV2();
				req.setBodyText("rets");
				response = stub.checkTextBodyV2(req);
				if(response!=null  && response.getDocumentSummary()!=null && response.getDocumentSummary().getMisspelledWord()!=null){
					Words []words =response.getDocumentSummary().getMisspelledWord();
					if(words.length>0){
					{ 
						for(int i=0;i<words.length;i++){
							if(words[i].getSuggestions()!=null){
								sug =words[i].getSuggestions();
								for(String word: sug){
									System.out.println(word);
								}	
							}
						}
					}
				}
			}
		}
		catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return sug;
	}
	
	public String getXMLResponse(){
		String xmlRes=null;
		try {
			if(response!=null){
				xmlRes = response.getOMElement(null, OMAbstractFactory.getOMFactory()).toStringWithConsume();
			}
		} catch (ADBException e) {

			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return xmlRes;
	}
	
	public static void main(String[] args) {
	}

}
