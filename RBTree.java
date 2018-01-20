public class RBTree {


    RBElement root = new RBElement();

    public RBTree()
    {
        root.left = null;
        root.right = null;
        root.color = "BLACK";
        root.p = null;
        root.key = "nil";
    }

    public void RBInsert(RBTree T, 
                         RBElement z)
    {
        RBElement y = new RBElement();
        RBElement x = T.root;

        while(!x.key.equals("nil"))
        {
            y = x;
            if(z.key.compareTo(x.key)<0)
            {
                x = x.left;
            }else{
                x = x.right;
            }
        }

        z.p = y;

        if(y.key.equals("nil"))
        {
            T.root = z;
        }else if(z.key.compareTo(y.key)<0){
            y.left = z;
        }else{
            y.right = z;
        }

        z.left = new RBElement();
        z.right = new RBElement();
        z.color = "RED";

        RBInsertFixUp(T, z);  

    }

    public void RBInsertFixUp(RBTree T, 
                              RBElement z)
    {
        while(z.p.color.equals("RED"))
        {
            if(z.p.key.equals(z.p.p.left.key))
            {
                RBElement y = z.p.p.right;
                if(y.color.equals("RED"))
                {
                    z.p.color = "BLACK";
                    y.color = "BLACK";
                    z.p.p.color = "RED";
                    z = z.p.p;
                }else{
                    if(z.key.equals(z.p.right.key))
                    {
                        z = z.p;
                        LeftRotate(T, z);
                    }
                    z.p.color = "BLACK";
                    z.p.p.color = "RED";
                    RightRotate(T, z.p.p);
                }
            }else{
                RBElement y = z.p.p.left;
                if(y.color.equals("RED"))
                {
                    z.p.color = "BLACK";
                    y.color = "BLACK";
                    z.p.p.color = "RED";
                    z = z.p.p;
                }else{
                    if(z.key.equals(z.p.left.key))
                    {
                        z = z.p;
                        RightRotate(T, z);
                    }
                    z.p.color = "BLACK";
                    z.p.p.color = "RED";
                    LeftRotate(T, z.p.p);
                }            
            }
        }
        T.root.color = "BLACK";
    }


    public void LeftRotate(RBTree T,
                           RBElement x)
    {
        RBElement y = x.right;
        x.right = y.left;

        if(!y.left.key.equals("nil"))
        {
            y.left.p = x;
        }

        y.p = x.p;

        if(x.p.key.equals("nil"))
        {
            T.root = y;
        }else if(x.key.equals(x.p.left.key)){
            x.p.left = y;
        }else{
            x.p.right = y;
        }

        y.left = x;
        x.p = y;

    }



    public void RightRotate(RBTree T,
                            RBElement x)
    {
        RBElement y = x.left;
        x.left = y.right;

        if(!y.right.key.equals("nil"))
        {
            y.right.p = x;
        }

        y.p = x.p;

        if(x.p.key.equals("nil"))
        {
            T.root = y;
        }else if(x.key.equals(x.p.right.key)){
            x.p.right = y;
        }else{
            x.p.left = y;
        }

        y.right = x;
        x.p = y;
    }

    public void RBTransplant(RBTree t, 
                             RBElement u, 
                             RBElement v)
    {

        if(u.p.key.equals("nil"))
        {
            t.root = v;
        }else if(u.key.equals(u.p.left.key)){
            u.p.left = v;
        }else{
            u.p.right = v;
        }
        v.p = u.p;


    }

    public RBElement TreeMinimun(RBElement x)
    {
        while(!x.left.key.equals("nil"))
        {
            x = x.left;
        }

        return x;
    }


    public RBElement TreeMaximum(RBElement x)
    {
        while(!x.right.key.equals("nil"))
        {
            x = x.right;
        }
        return x;
    }

    public boolean ElementExist(RBElement x, 
                                String k)
    {
        if(TreeSearch(x, k).key.equals("nil"))
        {
            return false;
        }else{
            return true;
        }
    }

    public RBElement TreeSearch(RBElement x, 
                                String k)
    {
        if(x.key.equals("nil") || k.equals(x.key))
        {
            return x;
        }
        if(k.compareTo(x.key)<0){
            return TreeSearch(x.left, k);
        }else{
            return TreeSearch(x.right, k);
        }
    }


    public void RBDeleteFixUP(RBTree t, 
                              RBElement x)
    {
        while(!x.key.equals(t.root.key) && x.color.equals("BLACK"))
        {
            if(x.key.equals(x.p.left.key))
            {
                RBElement w = x.p.right;
                if(w.color.equals("RED")){
                    w.color = "BLACK";
                    x.p.color = "RED";
                    LeftRotate(t, x.p);
                    w = x.p.right;
                }
                if(w.left.color.equals("BLACK") && w.right.color.equals("BLACK"))
                {
                    w.color = "RED";
                    x = x.p;
                }else{
                    if(w.right.color.equals("BLACK"))
                    {
                        w.left.color = "BLACK";
                        w.color = "RED";
                        RightRotate(t, w);
                        w = x.p.right;
                    }
                    w.color = x.p.color;
                    x.p.color = "BLACK";
                    w.right.color = "BLACK";
                    LeftRotate(t, x.p);
                    x = t.root;
                }
            }else{
                RBElement w = x.p.left;
                if(w.color.equals("RED"))
                {
                    w.color = "BLACK";
                    x.p.color = "RED";
                    RightRotate(t, x.p);
                    w = x.p.left;
                }
                if(w.left.color.equals("BLACK") && w.right.color.equals("BLACK"))
                {
                    w.color = "RED";
                    x = x.p;
                }else{
                    if(w.left.color.equals("BLACK")){
                        w.right.color = "BLACK";
                        w.color = "RED";
                        LeftRotate(t, w);
                        w = x.p.left;
                    }
                    w.color = x.p.color;
                    x.p.color = "BLACK";
                    w.left.color = "BLACK";
                    RightRotate(t, x.p);
                    x = t.root;
                }       
            }
        }
        x.color = "BLACK";
    }


    public void RBDelete(RBTree t, 
                         RBElement z)
    {
        RBElement y = z;
        String yoriginalcolor = y.color;
        if(z.left.key.equals("nil"))
        {
            RBElement x = z.right;
            RBTransplant(t, z, z.right);
            if(yoriginalcolor.equals("BLACK")){
                RBDeleteFixUP(t, x);
            }
        }else if(z.right.key.equals("nil")){
            RBElement x = z.left;
            RBTransplant(t, z, z.left);
            if(yoriginalcolor.equals("BLACK")){
                RBDeleteFixUP(t, x);
            }
        }else{
            y = TreeMinimun(z.right);
            yoriginalcolor = y.color;
            RBElement x = y.right;
            if(y.p.key.equals(z.key))
            {
                x.p = y;
            }else{
                RBTransplant(t, y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            RBTransplant(t, z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
            if(yoriginalcolor.equals("BLACK")){
                RBDeleteFixUP(t, x);
            }
        }

    }


    public void InorderTreeWalk(RBElement x)
    {
        if(!x.key.equals("nil"))
        {
            InorderTreeWalk(x.left);
            System.out.println(x.key);
            InorderTreeWalk(x.right);
        }
    }

    public void RBPrint(RBTree T)
    {
        RBElement x = T.root;
        InorderTreeWalk(x);
    }

    public int BlackDepth(RBElement x)
    {
        if(x.key.equals("nil"))
        {
            return 1;
        }else if(x.left.color.equals("BLACK")){
            return BlackDepth(x.left)+1;
        }else{
            return BlackDepth(x.left);
        }

    }

    public void RBCheck(RBElement x)
    {
        if(!x.key.equals("nil"))
        {
            int blackdepth = BlackDepth(x)-1;
            System.out.println("("+x.p.key+", "+x.key+", "+x.color+", "+blackdepth+", "+x.left.key+", "+x.right.key+")");            
            RBCheck(x.left);
            RBCheck(x.right);
        }
    }




}