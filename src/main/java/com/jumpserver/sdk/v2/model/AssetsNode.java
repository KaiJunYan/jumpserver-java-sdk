package com.jumpserver.sdk.v2.model;

public class AssetsNode {
    private String id;

    private String key;

    private String value;

    private String tree_parent;

    private String tree_id;

    private String org_id;

    private boolean is_node;

    private int assets_amount;

    private String[] assets;

    private String[] nodes;

    public String[] getNodes() {
        return nodes;
    }

    public void setNodes(String[] nodes) {
        this.nodes = nodes;
    }

    public String[] getAssets() {
        return assets;
    }

    public void setAssets(String[] assets) {
        this.assets = assets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTree_parent() {
        return tree_parent;
    }

    public void setTree_parent(String tree_parent) {
        this.tree_parent = tree_parent;
    }

    public String getTree_id() {
        return tree_id;
    }

    public void setTree_id(String tree_id) {
        this.tree_id = tree_id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public boolean getIs_node() {
        return is_node;
    }

    public void setIs_node(boolean is_node) {
        this.is_node = is_node;
    }

    public int getAssets_amount() {
        return assets_amount;
    }

    public void setAssets_amount(int assets_amount) {
        this.assets_amount = assets_amount;
    }
}
