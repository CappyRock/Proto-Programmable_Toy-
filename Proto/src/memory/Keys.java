package memory;

public class Keys {

	static String[][] allKeys = {{"increase", "1", "f"},
					{"decrease", "1", "f"},
					{"randomize", "1", "f"},
					{"reset", "1", "f"},
					{"set", "2", "f"},
					{"swap", "2", "f"},
					{"repeat", "1", "f"},
					{"while", "1", "s"},
					{"iff", "1", "s"},
					{"number", "2", "v"},
					{"name", "2", "v"},
					{"+", "2", "o"},
					{"-", "2", "o"},
					{"*", "2", "o"},
					{"/", "2", "o"},
					{"<", "2", "o"},
					{">", "2", "o"},
					{">", "2", "o"}};
	
	public static String[][] getKeys(){
		return allKeys;
	};
}
