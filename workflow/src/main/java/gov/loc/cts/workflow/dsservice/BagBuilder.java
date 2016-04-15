package gov.loc.cts.workflow.dsservice;

import gov.loc.cts.workflow.modelobjects.Bag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagBuilder {
	
	public static List<Bag> buildBagList(){
		//Bag(String projectId, String bagId, Long bagKey, Integer version)
		Bag bag1 = new Bag("CIP","00000000000G136C43","666907","1");
		Bag bag9 = new Bag("CIP","00000000000G136C43","666907","2");
		Bag bag2 = new Bag("AFP-AIP","0000000000K275C481","673761","1");
		Bag bag3 = new Bag("AMED AMRC","00000481324","337980","1");
		Bag bag4 = new Bag("DCS Record","0000000000K275C481","605184","1");
		Bag bag5 = new Bag("GMD Content","0000000000K275C481","436531","1");
		Bag bag6 = new Bag("World Digital Library","0000000000K275C481","436568","1");
		Bag bag7 = new Bag("World Digital Library","0000000000K275C481","436568","2");
		Bag bag8 = new Bag("World Digital Library","0000000000K275C481","436568","3");
		
		List<Bag> bagList = new ArrayList<Bag>();
		
		bagList.add(bag1);
		bagList.add(bag2);
		bagList.add(bag3);
		bagList.add(bag4);
		bagList.add(bag5);
		bagList.add(bag6);
		bagList.add(bag7);
		bagList.add(bag8);
		bagList.add(bag9);
		
		return bagList;
	}
	public static boolean checkBagExists(String projectId, String bagId){
		boolean available = false;
		List<Bag> lstbg =  buildBagList();
		for (Bag bag: lstbg) {
			if(bag.getProjectId().equals(projectId) && bag.getBagId().equals(bagId)){
				available=true;
				return available;
			}
		}
		return available;
	}
	public static boolean checkBagVersion(String projectId, String bagId, String version){
		boolean available = false;
		List<Bag> lstbg =  buildBagList();
		for (Bag bag: lstbg) {
			if(bag.getProjectId().equals(projectId) && bag.getBagId().equals(bagId) && bag.getVersion().equals(version)){
				available=true;
				return available;
			}
		}
		return available;
		
	}
	public static Map<String, Boolean> checkBagExistsNew(String projectId, String bagId, String version){
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("bagExists", false);
		map.put("bagVersionExists", false);
		
		List<Bag> lstbg =  buildBagList();
		for (Bag bag: lstbg) {
			if(bag.getProjectId().equals(projectId) && bag.getBagId().equals(bagId) ){
				map.put("bagExists", true);
				map.put("bagVersionExists", false);
				if(bag.getVersion().equals(version)){
					map.put("bagExists", true);
					map.put("bagVersionExists", true);
					return map;
				}
			}else if(!bag.getProjectId().equals(projectId) && !bag.getBagId().equals(bagId) && bag.getVersion().equals(version) ){
				map.put("bagExists", false);
				map.put("bagVersionExists", false);
			}
		}
		return map;
	}
	public static String checkBagExistsString(String projectId, String bagId, String version){
		String checkBag = "NewBag";
		List<Bag> lstbg =  buildBagList();
		List<Bag> lst = new ArrayList<Bag>();
		for (Bag bag: lstbg) {
			if(bag.getProjectId().equals(projectId) && bag.getBagId().equals(bagId)){
				
				lst.add(bag);
				System.out.println(lst.size());
				for (Bag bag1: lst) {
					if(bag1.getVersion().equals(version)){
						checkBag = "BagBxists";
						return checkBag;
					}else if(!bag1.getVersion().equals(version)){
						checkBag = "NewVersion";
					}
				}
				}else if(!bag.getProjectId().equals(projectId) && !bag.getBagId().equals(bagId) && !bag.getVersion().equals(version)){
					checkBag = "NewBag";
				}
		}
		System.out.println("From Handler ::: "+checkBag);
		return checkBag;
	}
	public static void saveBag(Bag bag){
		System.out.println("Bag Saved successfully!!!! ::: " +bag.getBagId());
	}
	public static void addBagDetails(Bag bag){
		System.out.println("Bag details added successfully!!!! ::: "+bag.getBagId());
	}
	public static void main(String[] args) {
		//boolean result = checkBagExists("CIP", "00000000000G136C43");
		//boolean result = checkBagVersion("CIP", "00000000000G136C43", "3");
		String str = checkBagExistsString("World Digital Library", "0000000000K275C481", "1");
		//System.out.println(result);
		System.out.println("Is Bag exist? :::: "+str);
		//System.out.println("Is Bag Version exist? :::: "+map.get("bagVersionExists"));
	}
}

