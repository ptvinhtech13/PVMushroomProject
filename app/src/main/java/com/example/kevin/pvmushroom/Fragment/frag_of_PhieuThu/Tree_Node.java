package com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu;

/**
 * Created by kevin on 2/16/2016.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.unnamed.b.atv.R;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.unnamed.b.atv.view.TreeNodeWrapperView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Tree_Node {
    public static final String NODES_ID_SEPARATOR = ":";

    private int mId;
    private int mLastId;
    private Tree_Node mParent;
    private boolean mSelected;
    private boolean mSelectable = true;
    private List<Tree_Node> children;
    private BaseNodeViewHolder mViewHolder;
    private Tree_NodeClickListener mClickListener;
    private Tree_NodeLongClickListener mLongClickListener;

    public void setmValue(Object mValue) {
        this.mValue = mValue;
    }

    private Object mValue;
    private boolean mExpanded;

    public static Tree_Node root() {
        Tree_Node root = new Tree_Node(null);
        root.setSelectable(false);
        return root;
    }

    private int generateId() {
        return ++mLastId;
    }

    public Tree_Node(Object value) {
        children = new ArrayList<>();

        mValue = value;
    }

    public Tree_Node addChild(Tree_Node childNode) {
        childNode.mParent = this;
        childNode.mId = generateId();
        children.add(childNode);
        return this;
    }

    public Tree_Node addChildren(Tree_Node... nodes) {
        for (Tree_Node n : nodes) {
            addChild(n);
        }
        return this;
    }

    public Tree_Node addChildren(Collection<Tree_Node> nodes) {
        for (Tree_Node n : nodes) {
            addChild(n);
        }
        return this;
    }

    public int deleteChild(Tree_Node child) {
        for (int i = 0; i < children.size(); i++) {
            if (child.mId == children.get(i).mId) {
                children.remove(i);
                return i;
            }
        }
        return -1;
    }
    public int deleteAllChild() {
        children = new ArrayList<>();
        return -1;
    }
    public int AddAllChild(List<Tree_Node> listTreeNode) {
        children = listTreeNode;
        return -1;
    }

    public List<Tree_Node> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public int size() {
        return children.size();
    }

    public Tree_Node getParent() {
        return mParent;
    }

    public int getId() {
        return mId;
    }

    public boolean isLeaf() {
        return size() == 0;
    }

    public Object getValue() {
        return mValue;
    }

    public boolean isExpanded() {
        return mExpanded;
    }

    public Tree_Node setExpanded(boolean expanded) {
        mExpanded = expanded;
        return this;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    public boolean isSelected() {
        return mSelectable && mSelected;
    }

    public void setSelectable(boolean selectable) {
        mSelectable = selectable;
    }

    public boolean isSelectable() {
        return mSelectable;
    }

    public String getPath() {
        final StringBuilder path = new StringBuilder();
        Tree_Node node = this;
        while (node.mParent != null) {
            path.append(node.getId());
            node = node.mParent;
            if (node.mParent != null) {
                path.append(NODES_ID_SEPARATOR);
            }
        }
        return path.toString();
    }


    public int getLevel() {
        int level = 0;
        Tree_Node root = this;
        while (root.mParent != null) {
            root = root.mParent;
            level++;
        }
        return level;
    }

    public boolean isLastChild() {
        if (!isRoot()) {
            int parentSize = mParent.children.size();
            if (parentSize > 0) {
                final List<Tree_Node> parentChildren = mParent.children;
                return parentChildren.get(parentSize - 1).mId == mId;
            }
        }
        return false;
    }

    public Tree_Node setViewHolder(BaseNodeViewHolder viewHolder) {
        mViewHolder = viewHolder;
        if (viewHolder != null) {
            viewHolder.mNode = this;
        }
        return this;
    }

    public Tree_Node setClickListener(Tree_NodeClickListener listener) {
        mClickListener = listener;
        return this;
    }

    public Tree_NodeClickListener getClickListener() {
        return this.mClickListener;
    }

    public Tree_Node setLongClickListener(Tree_NodeLongClickListener listener) {
        mLongClickListener = listener;
        return this;
    }

    public Tree_NodeLongClickListener getLongClickListener() {
        return mLongClickListener;
    }

    public BaseNodeViewHolder getViewHolder() {
        return mViewHolder;
    }

    public boolean isFirstChild() {
        if (!isRoot()) {
            List<Tree_Node> parentChildren = mParent.children;
            return parentChildren.get(0).mId == mId;
        }
        return false;
    }

    public boolean isRoot() {
        return mParent == null;
    }

    public Tree_Node getRoot() {
        Tree_Node root = this;
        while (root.mParent != null) {
            root = root.mParent;
        }
        return root;
    }

    public interface Tree_NodeClickListener {
        void onClick(Tree_Node node, Object value);
    }

    public interface Tree_NodeLongClickListener {
        boolean onLongClick(Tree_Node node, Object value);
    }

    public static abstract class BaseNodeViewHolder<E> {
        protected Android_Tree_View tView;
        protected Tree_Node mNode;
        private View mView;
        protected int containerStyle;
        protected Context context;

        public BaseNodeViewHolder(Context context) {
            this.context = context;
        }

        public View getView() {
            if (mView != null) {
                return mView;
            }
            final View nodeView = getNodeView();
            final TreeNodeWrapperView nodeWrapperView = new TreeNodeWrapperView(nodeView.getContext(), getContainerStyle());
            nodeWrapperView.insertNodeView(nodeView);
            mView = nodeWrapperView;

            return mView;
        }

        public void setTreeViev(Android_Tree_View treeViev) {
            this.tView = treeViev;
        }

        public Android_Tree_View getTreeView() {
            return tView;
        }

        public void setContainerStyle(int style) {
            containerStyle = style;
        }

        public View getNodeView() {
            return createNodeView(mNode, (E) mNode.getValue());
        }

        public ViewGroup getNodeItemsView() {
            return (ViewGroup) getView().findViewById(R.id.node_items);
        }

        public boolean isInitialized() {
            return mView != null;
        }

        public int getContainerStyle() {
            return containerStyle;
        }


        public abstract View createNodeView(Tree_Node node, E value);

        public void toggle(boolean active) {
            // empty
        }

        public void toggleSelectionMode(boolean editModeEnabled) {
            // empty
        }
    }
}

