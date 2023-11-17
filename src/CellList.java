public class CellList {
    class CellNode implements Cloneable{
        CellPhone phone;
        CellNode node;

        public CellNode () {
            phone = null;
            node = null;
        }

        public CellNode (CellPhone phone, CellNode node){
            this.phone = phone;
            this.node = node;
        }

        public CellNode (CellNode another){
            this.phone = another.phone;
            this.node = another.node;
        }

        public CellNode clone() throws CloneNotSupportedException {
            return (CellNode)super.clone();
        }
    }
}
