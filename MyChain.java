//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.stream.Stream;

import com.google.gson.GsonBuilder;

public class MyChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	
	public static String parseData(String line) {
		// parse data here to figure out how we parse string in block chain. I.e what attributes.
		// This code heavily depends on the format of the input
		return "";
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		//String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current hashes are not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous hashes are not equal");
				return false;
			}
			//check if hash is solved
			/**
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			*/
		}
		return true;
	}
	
	public static void main(String[] args) {	
		// first parse data from the blockchain file.
		//String filename = args[0];
		/**
		try (Stream<String> stream = Files.lines(Paths.get(filename), Charset.defaultCharset())) {
			  stream.forEach(line -> parseData(line));
			  //stream.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//add our blocks to the blockchain ArrayList:
		System.out.println("Inserting genesis block:");
		blockchain.add(new Block("0|SchneiderFarm>Merante|20.00|f3s1", "0"));
		
		System.out.println("Inserting block 1:");
		blockchain.add(new Block("1|Sunfresh>GiantEagle|100.00|xxr3",blockchain.get(blockchain.size()-1).hash));
		
		System.out.println("Inserting block 2:");
		blockchain.add(new Block("2|Heinz>McDonald|10.00|ab62",blockchain.get(blockchain.size()-1).hash));
		
		System.out.println("Blockchain valid: " + isChainValid());
		
		blockchain.get(2).data = "2|Heinz>McDonald|9.00|ab62";
		
		System.out.println("Blockchain valid: " + isChainValid());
		
		blockchain.get(2).data = "2|Heinz>McDonald|10.00|ab62";
		
		System.out.println("Blockchain valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("The block chain: ");
		System.out.println(blockchainJson);
	}
}