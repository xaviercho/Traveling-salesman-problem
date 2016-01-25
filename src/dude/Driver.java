/*
 * Name: Xavier Cho
 * Class: CSCE A311 - Data Structures & Algorithms
 * Instructor: Dr. Martin Cenek
 * Date: November 22, 2015
 * Homework #: 5
   Compiler: java 1.80_60
   IDE: Netbeans 8.02
SEARCHING THE CHEAPEST PATH algol: so the algorithm that was created searches by row and 
stores the first data given then from there compares the data in that row. If it finds a cheaper path, 
then it makes that the place you are going unless you have visited it already.
 * 
 * 
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Driver {
    static int  counter = 0;// use this to count the how many cities as well as the graph  for the index of costs for the edges
    static int  to_last = 0;//counter for the cities as the program is iterating thought the list of cities 
   
public static void main(String[] args) throws FileNotFoundException{
 find_path(); 
}

private static void find_path() throws FileNotFoundException{
 String fileName =  "AlaskaMilageChart_Test1 - Copy.csv"    ;// <<---  change this for each file in the directory you want to try
 //  "AlaskaMilageChart_Test1 - Copy.csv"   HERE IS MY  TEST CHANGED THE NUMBERS OF THE RECENT ONE
//    "AlaskaMilageChart.csv"    
 //   "AlaskaMilageChart_RoadClosures.csv" 
  
 // ^^^    USE THESE TO COPY AND PASTE TO EQUAL "fileName" to change and test the different files!!  ^^^^
 File file = new File (fileName);              
 Scanner inputStream = new Scanner(file);// reads the file specified 
 LLQueue cheapest_path = new LLQueue();//creates a queue to hold the data given.
 while(inputStream.hasNextLine()){
  inputStream.nextLine();
  counter++;
  }
 
 String[] city_options = new String[counter-1];//holds the names within the csv file 
 int[][] distance_costs = new int[counter-1][counter-1];// creates storage for the numbers in the csv file.
 int[][] store_proxy = new int[counter-1][counter-1];
 int city_left =0;
 int city_at =0;
 int total_cost =0;
 int closure = -1;
 int been_there_done_that = -1;

 city_options = store_cities(fileName);//stores the city names/vertexes in the csv file 
 distance_costs  = store_distances(fileName); //stores the cost of the edges/distances in the csv file 
  store_proxy  = store_distances(fileName); //stores the cost of the edges/distances in the csv file 
 
 while(city_at != closure){//checks to see if the cities are closed hence "closure == -1"
 to_last++;//counts how many cities you have been through
  cheapest_path.enqueue(city_options[city_at]);//get's the string names and puts them in cheapest path
  city_left = city_at;//when you leave sets as city_left
  if(to_last == 27){//adds variable going back home      
  total_cost += store_proxy[city_at][0];
  }
  city_at = closest_route(distance_costs,city_at);//see's to see the closest/cheapest way to the next city
    
   if(city_at!= been_there_done_that ){ //see's if you have been to the city before 
    total_cost += distance_costs[city_at][city_left] ;//if not adds the distance to the total
     for(int y = 0; y < counter-1; y++){
      distance_costs[city_left][y] = been_there_done_that;//checks off the cities
      }
    }
   }
   cheapest_path.display();//prints what is in the queue
   System.out.println( "["+city_options[0]+"]");
   
   System.out.println("\n total cost traveled : " + total_cost);//prints the total cost given  
}

private static String[] store_cities (String s){
 String[] cities = new String[counter-1];
 File file = new File (s);
          
try{
 Scanner inputStream;
 inputStream = new Scanner(file);// reads the file specified 
 String data = inputStream.nextLine();
 String[] values = data.split(",");//splits the data via comma's 
  
 for(int i = 0; i < counter-1; i++){//iterates through the data names
 cities[i] = values[i + 1];//enables the data to split and push the strings in the array
 }
    inputStream.close();//closes the file
    return cities;//reaturns the stored array
}
 catch (FileNotFoundException ex){
 Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);//catches if anything happens to be wrong
 return cities;
 }     
}
      
private static int[][] store_distances(String s){
 String[][] store_map = new String [counter][counter];
 int[][] cost = new int[counter-1][counter-1];
 
 try{
  File file = new File (s);
  Scanner inputStream;
  inputStream = new Scanner(file);//sets the files to be read in find_path
          
  for(int x = 0; x < counter; x++){ 
   String data = inputStream.nextLine();
   String[] values = data.split(",");//splits the data  with a comma 
   System.arraycopy(values, 0, store_map[x], 0, counter); 
   }
             
  for(int i = 1; i < counter; i++){  //checks the rows 
   for(int j = 1; j < counter; j++){//checks the comlumns 
     cost[i - 1][j - 1] = Integer.parseInt(store_map[i][j]); //parses the the grid of numbers 
     }
    }
    inputStream.close(); //closes the file 
    return cost;// returns the grid of numbers in a 2d array  
 }
catch (FileNotFoundException ex){
 Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
           return cost;
       }
   }
   
private static int closest_route(int[][]map, int this_place){// takes in the 2d array  and an int & tries to find the cheapest route.
 int leaving = this_place;
 int where_you_are_going = -1;
 int lowest_cost = 0;
 for(int i = 0; i < 27; i++) {		
  if(map[i][leaving] > 0) {
   lowest_cost = map[i][leaving];
   where_you_are_going = i;
   break;
   }
  }
      
if(where_you_are_going == -1){//checks to see if you have been there
  return where_you_are_going;
}
      
for(int i = where_you_are_going; i < counter-1; i++){	
 if(map[i][leaving] <= lowest_cost && map[i][leaving] > 0 ) {//checks to see the shortest path
  lowest_cost = map[leaving][i];
  where_you_are_going = i;
 }
}
 return where_you_are_going;
 } 
}

