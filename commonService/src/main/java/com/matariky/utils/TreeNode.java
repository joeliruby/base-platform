package com.matariky.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tree nodes, all those who need to realize the tree nodes need to inherit this
 * class
 *
 * @since 1.0.0
 */
public class TreeNode<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Primary Key
     */
    private Long id;
    /**
     * Superior ID
     */
    private Long pid;
    /**
     * Sub -node Pagination
     */

    private Long sortOrder;
    private List<T> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }
}