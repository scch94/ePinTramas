package messagefactory;

import com.ericsson.otp.erlang.*;
import java.util.*;
public class CreacionMensaje {
	public static OtpErlangList mensajenuevo() {
		// Creación del mensaje
		List< Map<String, String> > javaList = new ArrayList< Map<String, String> >();
		javaList.add( new HashMap<String, String>() {{ 
				put("utfi", "utfi---");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("connection_name", "ConnectionName--");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("user", "User-Agent");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("agent", "AgentRechargeIdentifier");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("subscriber", "18763759178");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("origin_node_type", "EXT");
		}});javaList.add( new HashMap<String, String>() {{ 
			put("origin_host_name", "appjmxx01");
		}});javaList.add( new HashMap<String, String>() {{ 
			put("origin_transaction_id", "1745844347");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("currency", "JMD");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("amount", "10000");}});
		javaList.add( new HashMap<String, String>() {{ 
			put("profile_id", "JM");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("external_data_1", "EPIN_Jamaica");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("external_data_2", "Scotiabank Distributor Account");
		}});javaList.add( new HashMap<String, String>() {{ 
			put("external_data_3", "1745844347");
		}});
		javaList.add( new HashMap<String, String>() {{ 
			put("external_data_4", "000000306991087");
		}});
		// convierte la lista de Java en una OtpErlangList
		OtpErlangObject[] erlElements = new OtpErlangObject[javaList.size()];
		for (int i = 0; i < javaList.size(); i++) { 
			Map<String, String> javaMap = javaList.get(i); 
			OtpErlangObject[] erlTuples = new OtpErlangObject[javaMap.size()]; 
			int j = 0; for (Map.Entry<String, String> entry : javaMap.entrySet()) {
				OtpErlangObject[] erlPair = new OtpErlangObject[2];
				erlPair[0] = new OtpErlangAtom(entry.getKey());
				erlPair[1] = new OtpErlangAtom(entry.getValue()); 
				erlTuples[j++] = new OtpErlangTuple(erlPair); 
			} 
			erlElements[i] = new OtpErlangTuple(erlTuples);
		}
		OtpErlangList erlList = new OtpErlangList(erlElements);
		return erlList;
	}
}
