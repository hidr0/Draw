import java.util.Random;


public class ChooseRandomString {
	public static void main(String[] args) {
		String[] places={"BLOW","JAZZMATAZZ"};
		for(int i=0;i<9;i++){
		String random = (places[new Random().nextInt(places.length)]);
		System.out.println(random);
		}
	}
}