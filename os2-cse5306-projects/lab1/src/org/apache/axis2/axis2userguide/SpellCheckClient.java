package org.apache.axis2.axis2userguide;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.axis2userguide.CheckStub.CheckTextBodyV2;
import org.apache.axis2.axis2userguide.CheckStub.CheckTextBodyV2Response;
import org.apache.axis2.axis2userguide.CheckStub.Words;




public class SpellCheckClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			CheckStub stub = new CheckStub();
			CheckTextBodyV2 req = new CheckTextBodyV2();
			req.setBodyText("rets");
			CheckTextBodyV2Response response = stub.checkTextBodyV2(req);
			Words []words =response.getDocumentSummary().getMisspelledWord() ;
			for(int i=0;i<words.length;i++){
				String []sug =words[i].getSuggestions();
				for(String word: sug){
					System.out.println(word);
				}
			
			}
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
