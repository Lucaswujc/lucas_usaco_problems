package sortingalgorithmns;

public class ListDemo {
    class List {

        ListItem currentPostion;
        ListItem tailPosition;
        ListItem headPosition;

        List() {
            ListItem HEAD = ListItem(-99999);
            headPosition = HEAD;
            endPosition = headPosition;
            currentPostion = headPosition;
        }

        void addItemAtEnd(int itemvalue) {
            ListItem endItem = ListItem(itemvalue);
            endPosition.nexItem = endItem;
            endPosition = endItem;
        }

        int getCurrentItem() {
            return currentPostion.value;
        }

        void moveToNext() {
            if (currentPostion == this.tailPosition)
                System.out.println("Error, alread at tail");
            else currentPostion = currentPostion.nexItem;
        }

        void movePrev() {
            if (currentPostion == this.headPosition )
                System.out.println("Error already at head");
            else currentPostion = currentPostion.prevItem;
        }
    }
    class ListItem {
        int value;
        ListItem nexItem;
        ListItem prevItem;

        ListItem(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        List mylist = new List();
        mylist.addItemAtEnd(1);
        mylist.getCurrentItem();
        myllist.getNextItem();
    }
}
