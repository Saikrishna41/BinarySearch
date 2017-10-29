import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main implements Comparable{

	CommandLine cmd = null;
	String type;
	int middle,iKey;
	Integer[] iList;
	String aList[],key;
	boolean output;
	public Main(String[] args) {
			if(validateOptions(args)) {
				   validate();
			}
		}

	public int compareTo(Object o) {
		return 0;
	}	
	
	
	private boolean binSearch(Comparable[] aList, Comparable key) {
		int intialValue=0;
		int finalValue=aList.length-1;
		
		while(intialValue<=finalValue) {
			
			int middle=(intialValue+finalValue)/2;
			
			if(aList[middle].equals(key)) {
				output=true;
			}
			if(aList[middle].compareTo(key)<0) {
				intialValue=middle+1;
			}
			else {
			finalValue=middle-1;
			}
		}
		
		return output;
	}
	
	
		

	private boolean validateOptions(String[] args) {
		Options options = new Options();
		options.addOption(Option.builder("type").desc("List of elements").longOpt("type").numberOfArgs(1).build());
		options.addOption(Option.builder("key").desc("Element to Search").longOpt("key").numberOfArgs(1).build());
		options.addOption(Option.builder("list").desc("List of elements").longOpt("list").numberOfArgs(Option.UNLIMITED_VALUES).build());
		CommandLineParser parser = new DefaultParser();
		try {
			cmd=parser.parse(options,args);
		}
		catch(Exception e) {e.printStackTrace();return false;}
		type=cmd.getOptionValue("type");
		key=cmd.getOptionValue("key");
		aList =cmd.getOptionValues("list");
		
		return true;
	}
	
	
	private void validate() {
	

		if(type.equals("i"))
		    {
			try {
		        iKey = Integer.parseInt(key);
		        iList = new Integer[aList.length];
		        for (int i = 0; i < aList.length; i++)
		            iList[i] = Integer.parseInt(aList[i]);
		        }
				catch(Exception e) {System.out.println("Please enter integers to sort");}
			/* for (int i = 0; i < iList.length; i++) {
	                for (int j = i + 1; j <iList.length; j++) {
	                    int tmp = 0;
	                    if (iList[i] > iList[j]) {
	                        tmp = iList[i];
	                        iList[i] = iList[j];
	                        iList[j] = tmp;
	                    }
	                }
	            }*/
				Arrays.sort(iList);
		        output = binSearch(iList,iKey);
		        printOutput();
		    }
		    else if(type.equals("s"))
		    {
		    		Arrays.sort(aList);
		        output = binSearch(aList,key);
		        printOutput();
		    }
		    else {System.out.println("Please enter a valid argument");}
		}
			

	private void printOutput() {
		System.out.print("Output:");
		if(output) {
			System.out.println("1");	
		}
		else {
			System.out.println("0");
		}
	}

	public static void main(String[] args) {
		
		new Main(args);
	}

	
}
