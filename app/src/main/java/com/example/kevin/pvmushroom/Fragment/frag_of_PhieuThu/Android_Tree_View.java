package com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.unnamed.b.atv.R;
import com.unnamed.b.atv.holder.SimpleViewHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.TwoDScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Created by kevin on 2/16/2016.
 */
public class Android_Tree_View {
    private static final String NODES_PATH_SEPARATOR = ";";

    protected Tree_Node mRoot;
    private Context mContext;
    private boolean applyForRoot;
    private int containerStyle = 0;
    private Class<? extends TreeNode.BaseNodeViewHolder> defaultViewHolderClass = SimpleViewHolder.class;
    private Tree_Node.Tree_NodeClickListener nodeClickListener;
    private Tree_Node.Tree_NodeLongClickListener nodeLongClickListener;
    private boolean mSelectionModeEnabled;
    private boolean mUseDefaultAnimation = false;
    private boolean use2dScroll = false;

    public Android_Tree_View(Context context) {
        mContext = context;
    }

    public void setRoot(Tree_Node mRoot) {
        this.mRoot = mRoot;
    }

    public Android_Tree_View(Context context, Tree_Node root) {
        mRoot = root;
        mContext = context;
    }

    public void setDefaultAnimation(boolean defaultAnimation) {
        this.mUseDefaultAnimation = defaultAnimation;
    }

    public void setDefaultContainerStyle(int style) {
        setDefaultContainerStyle(style, false);
    }

    public void setDefaultContainerStyle(int style, boolean applyForRoot) {
        containerStyle = style;
        this.applyForRoot = applyForRoot;
    }

    public void setUse2dScroll(boolean use2dScroll) {
        this.use2dScroll = use2dScroll;
    }

    public boolean is2dScrollEnabled() {
        return use2dScroll;
    }

    public void setDefaultViewHolder(Class<? extends TreeNode.BaseNodeViewHolder> viewHolder) {
        defaultViewHolderClass = viewHolder;
    }

    public void setDefaultNodeClickListener(Tree_Node.Tree_NodeClickListener listener) {
        nodeClickListener = listener;
    }

    public void setDefaultNodeLongClickListener(Tree_Node.Tree_NodeLongClickListener listener) {
        nodeLongClickListener = listener;
    }

    public void expandAll() {
        expandNode(mRoot, true);
    }

    public void collapseAll() {
        for (Tree_Node n : mRoot.getChildren()) {
            collapseNode(n, true);
        }
    }


    public View getView(int style) {
        final ViewGroup view;
        if (style > 0) {
            ContextThemeWrapper newContext = new ContextThemeWrapper(mContext, style);
            view = use2dScroll ? new TwoDScrollView(newContext) : new ScrollView(newContext);
        } else {
            view = use2dScroll ? new TwoDScrollView(mContext) : new ScrollView(mContext);
        }

        Context containerContext = mContext;
        if (containerStyle != 0 && applyForRoot) {
            containerContext = new ContextThemeWrapper(mContext, containerStyle);
        }
        final LinearLayout viewTreeItems = new LinearLayout(containerContext, null, containerStyle);

        viewTreeItems.setId(R.id.tree_items);
        viewTreeItems.setOrientation(LinearLayout.VERTICAL);
        view.addView(viewTreeItems);

        mRoot.setViewHolder(new Tree_Node.BaseNodeViewHolder(mContext) {
            @Override
            public View createNodeView(Tree_Node node, Object value) {
                return null;
            }

            @Override
            public ViewGroup getNodeItemsView() {
                return viewTreeItems;
            }
        });

        expandNode(mRoot, false);
        return view;
    }

    public View getView() {
        return getView(-1);
    }


    public void expandLevel(int level) {
        for (Tree_Node n : mRoot.getChildren()) {
            expandLevel(n, level);
        }
    }

    private void expandLevel(Tree_Node node, int level) {
        if (node.getLevel() <= level) {
            expandNode(node, false);
        }
        for (Tree_Node n : node.getChildren()) {
            expandLevel(n, level);
        }
    }

    public void expandNode(Tree_Node node) {
        expandNode(node, false);
    }

    public void collapseNode(Tree_Node node) {
        collapseNode(node, false);
    }

    public String getSaveState() {
        final StringBuilder builder = new StringBuilder();
        getSaveState(mRoot, builder);
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 1);
        }
        return builder.toString();
    }

    public void restoreState(String saveState) {
        if (!TextUtils.isEmpty(saveState)) {
            collapseAll();
            final String[] openNodesArray = saveState.split(NODES_PATH_SEPARATOR);
            final Set<String> openNodes = new HashSet<>(Arrays.asList(openNodesArray));
            restoreNodeState(mRoot, openNodes);
        }
    }

    private void restoreNodeState(Tree_Node node, Set<String> openNodes) {
        for (Tree_Node n : node.getChildren()) {
            if (openNodes.contains(n.getPath())) {
                expandNode(n);
                restoreNodeState(n, openNodes);
            }
        }
    }

    private void getSaveState(Tree_Node root, StringBuilder sBuilder) {
        for (Tree_Node node : root.getChildren()) {
            if (node.isExpanded()) {
                sBuilder.append(node.getPath());
                sBuilder.append(NODES_PATH_SEPARATOR);
                getSaveState(node, sBuilder);
            }
        }
    }

    private void toggleNode(Tree_Node node) {
        if (node.isExpanded()) {
            collapseNode(node, false);
        } else {
            expandNode(node, false);
        }

    }

    private void collapseNode(Tree_Node node, final boolean includeSubnodes) {
        node.setExpanded(false);
        Tree_Node.BaseNodeViewHolder nodeViewHolder = getViewHolderForNode(node);

        if (mUseDefaultAnimation) {
            collapse(nodeViewHolder.getNodeItemsView());
        } else {
            nodeViewHolder.getNodeItemsView().setVisibility(View.GONE);
        }
        nodeViewHolder.toggle(false);
        if (includeSubnodes) {
            for (Tree_Node n : node.getChildren()) {
                collapseNode(n, includeSubnodes);
            }
        }
    }

    private void expandNode(final Tree_Node node, boolean includeSubnodes) {
        node.setExpanded(true);
        final Tree_Node.BaseNodeViewHolder parentViewHolder = getViewHolderForNode(node);
        parentViewHolder.getNodeItemsView().removeAllViews();


        parentViewHolder.toggle(true);

        for (final Tree_Node n : node.getChildren()) {
            addNode(parentViewHolder.getNodeItemsView(), n);

            if (n.isExpanded() || includeSubnodes) {
                expandNode(n, includeSubnodes);
            }

        }
        if (mUseDefaultAnimation) {
            expand(parentViewHolder.getNodeItemsView());
        } else {
            parentViewHolder.getNodeItemsView().setVisibility(View.VISIBLE);
        }

    }

    private void addNode(ViewGroup container, final Tree_Node n) {
        final Tree_Node.BaseNodeViewHolder viewHolder = getViewHolderForNode(n);
        final View nodeView = viewHolder.getView();
        container.addView(nodeView);
        if (mSelectionModeEnabled) {
            viewHolder.toggleSelectionMode(mSelectionModeEnabled);
        }

        nodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n.getClickListener() != null) {
                    n.getClickListener().onClick(n, n.getValue());
                } else if (nodeClickListener != null) {
                    nodeClickListener.onClick(n, n.getValue());
                }
                toggleNode(n);
            }
        });

        nodeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (n.getLongClickListener() != null) {
                    return n.getLongClickListener().onLongClick(n, n.getValue());
                } else if (nodeLongClickListener != null) {
                    return nodeLongClickListener.onLongClick(n, n.getValue());
                }
                return false;
            }
        });
    }

    //------------------------------------------------------------
    //  Selection methods

    public void setSelectionModeEnabled(boolean selectionModeEnabled) {
        if (!selectionModeEnabled) {
            // TODO fix double iteration over tree
            deselectAll();
        }
        mSelectionModeEnabled = selectionModeEnabled;

        for (Tree_Node node : mRoot.getChildren()) {
            toggleSelectionMode(node, selectionModeEnabled);
        }

    }

    public <E> List<E> getSelectedValues(Class<E> clazz) {
        List<E> result = new ArrayList<>();
        List<Tree_Node> selected = getSelected();
        for (Tree_Node n : selected) {
            Object value = n.getValue();
            if (value != null && value.getClass().equals(clazz)) {
                result.add((E) value);
            }
        }
        return result;
    }

    public boolean isSelectionModeEnabled() {
        return mSelectionModeEnabled;
    }

    private void toggleSelectionMode(Tree_Node parent, boolean mSelectionModeEnabled) {
        toogleSelectionForNode(parent, mSelectionModeEnabled);
        if (parent.isExpanded()) {
            for (Tree_Node node : parent.getChildren()) {
                toggleSelectionMode(node, mSelectionModeEnabled);
            }
        }
    }

    public List<Tree_Node> getSelected() {
        if (mSelectionModeEnabled) {
            return getSelected(mRoot);
        } else {
            return new ArrayList<>();
        }
    }

    // TODO Do we need to go through whole tree? Save references or consider collapsed nodes as not selected
    private List<Tree_Node> getSelected(Tree_Node parent) {
        List<Tree_Node> result = new ArrayList<>();
        for (Tree_Node n : parent.getChildren()) {
            if (n.isSelected()) {
                result.add(n);
            }
            result.addAll(getSelected(n));
        }
        return result;
    }

    public void selectAll(boolean skipCollapsed) {
        makeAllSelection(true, skipCollapsed);
    }

    public void deselectAll() {
        makeAllSelection(false, false);
    }

    private void makeAllSelection(boolean selected, boolean skipCollapsed) {
        if (mSelectionModeEnabled) {
            for (Tree_Node node : mRoot.getChildren()) {
                selectNode(node, selected, skipCollapsed);
            }
        }
    }

    public void selectNode(Tree_Node node, boolean selected) {
        if (mSelectionModeEnabled) {
            node.setSelected(selected);
            toogleSelectionForNode(node, true);
        }
    }

    private void selectNode(Tree_Node parent, boolean selected, boolean skipCollapsed) {
        parent.setSelected(selected);
        toogleSelectionForNode(parent, true);
        boolean toContinue = skipCollapsed ? parent.isExpanded() : true;
        if (toContinue) {
            for (Tree_Node node : parent.getChildren()) {
                selectNode(node, selected, skipCollapsed);
            }
        }
    }

    private void toogleSelectionForNode(Tree_Node node, boolean makeSelectable) {
        Tree_Node.BaseNodeViewHolder holder = getViewHolderForNode(node);
        if (holder.isInitialized()) {
            getViewHolderForNode(node).toggleSelectionMode(makeSelectable);
        }
    }

    private Tree_Node.BaseNodeViewHolder getViewHolderForNode(Tree_Node node) {
        Tree_Node.BaseNodeViewHolder viewHolder = node.getViewHolder();
        if (viewHolder == null) {
            try {
                final Object object = defaultViewHolderClass.getConstructor(Context.class).newInstance(mContext);
                viewHolder = (Tree_Node.BaseNodeViewHolder) object;
                node.setViewHolder(viewHolder);
            } catch (Exception e) {
                throw new RuntimeException("Could not instantiate class " + defaultViewHolderClass);
            }
        }
        if (viewHolder.getContainerStyle() <= 0) {
            viewHolder.setContainerStyle(containerStyle);
        }
        if (viewHolder.getTreeView() == null) {
            viewHolder.setTreeViev(this);
        }
        return viewHolder;
    }

    private static void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    private static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    //-----------------------------------------------------------------
    //Add / Remove

    public void addNode(Tree_Node parent, final Tree_Node nodeToAdd) {
        parent.addChild(nodeToAdd);
        if (parent.isExpanded()) {
            final Tree_Node.BaseNodeViewHolder parentViewHolder = getViewHolderForNode(parent);
            addNode(parentViewHolder.getNodeItemsView(), nodeToAdd);
        }
    }

    public void removeNode(Tree_Node node) {
        if (node.getParent() != null) {
            Tree_Node parent = node.getParent();
            int index = parent.deleteChild(node);
            if (parent.isExpanded() && index >= 0) {
                final Tree_Node.BaseNodeViewHolder parentViewHolder = getViewHolderForNode(parent);
                parentViewHolder.getNodeItemsView().removeViewAt(index);
            }
        }
    }
}
