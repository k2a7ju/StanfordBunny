package bunny;

public class Example extends Object{
    
    public static void main(String[] arguments){
	int x = 200;
	int y = 200;

	StanfordBunny.open(x += 25, y += 25);

	return;
    }
}
