import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Main {

	static int trackCount;
	static int selectionCount;
	static List<Long> number_of_listened = new ArrayList<Long>();
	static List<String> song_name = new ArrayList<String>();
	static List<Double> zipf_value = new ArrayList<Double>();
	static List<String> best_songs = new ArrayList<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		getInput();
		calculateZipf();
		getBestSongs();

	}
	private static void getBestSongs() {
		// TODO Auto-generated method stub
		List<Double> temp_zipf = new ArrayList<Double>(zipf_value);
		//Collections.copy(temp_zipf, zipf_value);
		Collections.sort(temp_zipf);
		Collections.reverse(temp_zipf);
		for(int i=0; i<selectionCount; i++)
		{
			for(int j=0; j < zipf_value.size(); j++)
			{
				if(temp_zipf.get(i).equals(zipf_value.get(j)))
				{
					best_songs.add(song_name.get(j));
					zipf_value.set(j, (double) -1);
					break;
				}
			}
		}
		for( String s : best_songs)
			System.out.print(s + "\n");
	}
	private static void calculateZipf() {
		// TODO Auto-generated method stub
		
		double zipf=0;
		for (int i=0 ; i < number_of_listened.size(); i++)
		{
		
			zipf=number_of_listened.get(i).longValue()*(i+1);
			zipf_value.add(zipf);
		}
	}
	private static void getInput()
	{
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int i=0;
			while((input=br.readLine())!=null){
				//System.out.println(input);
				String[] parts = input.split(" ");
				if(i==0)
				{
					trackCount = Integer.parseInt(parts[0]);
					selectionCount = Integer.parseInt(parts[1]);
					//System.out.println("track count:" +trackCount +" and selectionCount:"+ selectionCount );
				}
				else
				{
					number_of_listened.add(Long.parseLong(parts[0].toString()));
					song_name.add(parts[1]);
					//System.out.println("number_of_listened:" +parts[0] +" and song_name:"+ parts[1] );
				}
				i++;
				if(i==trackCount+1)
					return;
			}
	 
		}catch(IOException io){
			io.printStackTrace();
		}	
	}
}
