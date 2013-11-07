import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Zipf {

	static int trackCount;
	static int selectionCount;
	static List<Zipf.Song> song_list = new ArrayList<Zipf.Song>();
	private class  Song implements Comparable<Song>{
		public Long number_of_listened;
		public String song_name;
		public Double zipf_value;
		public Integer position;
		@Override
		public int compareTo(Song arg0) {
			Double p1 = this.zipf_value;
			Double p2 = arg0.zipf_value;

			  if (p1 > p2 ){
			   return 1;
			  }
			  else if (p1 < p2){
			   return -1;
			  }
			  else {
				 int q1 = this.position;
				 int q2 = arg0.position;
				  if (q1 < q2 ){
					   return 1;
				  }
				  else if (q1 > q2){
					   return -1;
				  }
				  else
					  return 0;
			  }
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Zipf zipf = new Zipf();
		zipf.getInput();
		zipf.calculateZipf();
		zipf.getBestSongs();

	}
	private  void getBestSongs() {
		// TODO Auto-generated method stub
		//Collections.copy(temp_zipf, zipf_value);
	
		Collections.sort(song_list);
		Collections.reverse(song_list);
		
		for( int i=0;i<selectionCount;i++)
			System.out.print(song_list.get(i).song_name + "\n");
	}
	private  void calculateZipf() {
		// TODO Auto-generated method stub
		
		for (int i=0 ; i < trackCount; i++)
		{
			song_list.get(i).zipf_value= (double) (song_list.get(i).number_of_listened *(i+1));
			
		}
	}
	private void getInput()
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
					Song song = this.new Song();
					song.number_of_listened = Long.parseLong(parts[0].toString());
					song.song_name = parts[1];
					song.position = i;
					song_list.add(song);
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
