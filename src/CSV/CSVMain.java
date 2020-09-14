package CSV;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CSVMain {

	public static void main(String[] args) {

		String filePath = "sample.csv";

		List<List<String>> fileRecords = CSVReader.getData(filePath);

		int groupMember1 = 1;
		int groupMember2 = 2;
		String letterToFind = "a";
		int namesContainingACount = 0;
		String regex = ".*\\d.*";

		for (List<String> namesContainingA : fileRecords) { // Loop through the 2nd and 3rd column of the table and count how many names contain the letter a
			if (!namesContainingA.get(groupMember1).matches(regex)) { // Check if the name contains a number. If it does, don't count it (Skips 1st row)
				if (namesContainingA.get(groupMember1).toLowerCase().contains(letterToFind)) {
					namesContainingACount++;
				}
				if (namesContainingA.get(groupMember2).toLowerCase().contains(letterToFind)) {
					namesContainingACount++;
				} 
			}
		}
		
		System.out.println("There are " + namesContainingACount + " names containing the letter " + letterToFind + "\n");
		
		//---------------------------------------

		int workWithColumn = 6;
		String androidString = "Android App";
		int workWithAndroidCount = 0;
		
		for (List<String> workWithAndroid : fileRecords) { // Loop through the table and count the number of rows containing Android App
			if (workWithAndroid.get(workWithColumn).equals(androidString)) {
				workWithAndroidCount++;
			}
		}
		
		System.out.println("There are " + workWithAndroidCount + " groups that chose to work with Android App.\n");
		
		//---------------------------------------
		
		int timestampColumn = 0;
		List<String> timestampList = new ArrayList<String>();
		
		for (List<String> addTimestampsToList : fileRecords) { // Save the timestamps column to a separate list
			if (addTimestampsToList.get(timestampColumn).matches(regex)) { // Check if the timestamp contains a number to skip 1st row
				timestampList.add(addTimestampsToList.get(timestampColumn));
			}
		}
		
		List<String> duplicateTimestamps = new ArrayList<String>();
		HashSet<String> unique = new HashSet<String>();
		
		for (String findDuplicates : timestampList) { // Check for duplicates within timestampList and add them to duplicateTimestamps
			if (!unique.add(findDuplicates)) {
				duplicateTimestamps.add(findDuplicates);
			}
		}
		
		List<String> duplicateTimestampNames = new ArrayList<String>();
		
		for (List<String> findDuplicateNames : fileRecords) { // Match duplicate timestamps with the names in the original table and add them to duplicateTimestampNames
			for (int i = 0; i < duplicateTimestamps.size(); i++) {
				if (findDuplicateNames.get(timestampColumn).equals(duplicateTimestamps.get(i))) {
					duplicateTimestampNames.add(findDuplicateNames.get(groupMember1) + " / " + findDuplicateNames.get(groupMember2));
				} 
			}
		}
		
		System.out.println("Groups with duplicate timestamps are:");
		for (int i = 0; i < duplicateTimestampNames.size(); i++) {
			System.out.println(duplicateTimestampNames.get(i));
		}
		
	}

}
