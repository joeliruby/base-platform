package com.matariky.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.matariky.commonservice.upload.utils.RenException;
import com.matariky.constant.RedisKey;
import com.matariky.redis.RedisUtils;


/**
 * Tree structure utility class, such as: menu, department, etc.
 *
 * @since 1.0.0
 */
public class TreeUtils {

    /**
     * Build tree nodes based on pid
     */
    public static <T extends TreeNode<?>> List<T> build(List<T> treeNodes, Long pid) {
        //pid cannot be null
        if (pid == null) {
            throw new RenException("pid is null!!");
        }

        List<T> treeList = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (pid.equals(treeNode.getPid())) {
                treeList.add(findChildren(treeNodes, treeNode));
            }
        }

        return treeList;
    }

    /**
     * Find child nodes
     */
    private static <T extends TreeNode> T findChildren(List<T> treeNodes, T rootNode) {
        for (T treeNode : treeNodes) {
            if (rootNode.getId().equals(treeNode.getPid())) {
                rootNode.getChildren().add(findChildren(treeNodes, treeNode));
            }
        }
        return rootNode;
    }

    /**
     * Build tree nodes
     */
    public static <T extends TreeNode> List<T> build(List<T> treeNodes, RedisUtils redisUtils, String locale) {
        List<T> result = new ArrayList<>();


        //list to map
        Map<Long, T> nodeMap = new ConcurrentHashMap<>(treeNodes.size());

        //put all nodes in a map
        for (T treeNode : treeNodes) {
            nodeMap.put(treeNode.getId(), treeNode);
        }

        Iterator<T> iterator = nodeMap.values().iterator();
        while (iterator.hasNext()) {
            T node = iterator.next();
            String menuName = (String) redisUtils.hGet(RedisKey.MENU_NAMES + locale, node.getId().toString());
            if (menuName != null) {
                try {
                    Class<? extends TreeNode> readerClass = node.getClass();
                    Field field = readerClass.getDeclaredField("name");
                    field.setAccessible(true);
                    field.set(node, menuName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            T parent = nodeMap.get(node.getPid());
            if (parent != null) {
                parent.getChildren().add(node);
                Collections.sort((List<TreeNode>) parent.getChildren(), new Comparator<TreeNode>() {
                    @Override
                    public int compare(TreeNode o1, TreeNode o2) {
                        // If no sort order is set, set it to maximum, sort at the end
                        if (o1.getSortOrder() == null) {
                            o1.setSortOrder(Long.MIN_VALUE);
                        }
                        if (o2.getSortOrder() == null) {
                            o2.setSortOrder(Long.MIN_VALUE);
                        }
                        return Long.compare(o1.getSortOrder(), o2.getSortOrder());
                    }
                });
            } else {
                result.add(node);
            }
        }
        result.sort((a,b) ->  (a.getSortOrder()!=null ? a.getSortOrder() :  Long.valueOf(Long.MIN_VALUE)).compareTo((b.getSortOrder()!=null ? b.getSortOrder() : Long.valueOf(Long.MIN_VALUE))));
        return result;
    }


    /**
     * Build tree nodes
     */
    public static <T extends TreeNode> List<T> build(List<T> treeNodes) {
        List<T> result = new ArrayList<>();


        //list to map
        Map<Long, T> nodeMap = new ConcurrentHashMap<>(treeNodes.size());

        //put all nodes in a map
        for (T treeNode : treeNodes) {
            nodeMap.put(treeNode.getId(), treeNode);
        }

        Iterator<T> iterator = nodeMap.values().iterator();
        while (iterator.hasNext()) {
            T node = iterator.next();
            T parent = nodeMap.get(node.getPid());
            if (parent != null) {
                List<T> children = parent.getChildren();
                children.add(node);
                // Sort child nodes
                Collections.sort(children, new Comparator<TreeNode>() {
                    @Override
                    public int compare(TreeNode o1, TreeNode o2) {
                        // If no sort order is set, set it to maximum, sort at the end
                        if (o1.getSortOrder() == null) {
                            o1.setSortOrder(Long.MIN_VALUE);
                        }
                        if (o2.getSortOrder() == null) {
                            o2.setSortOrder(Long.MIN_VALUE);
                        }
                        return Long.compare(o1.getSortOrder(), o2.getSortOrder());
                    }
                });
            } else {
                result.add(node);
            }
        }
        // Sort top-level nodes
        result.sort((a,b) ->  (a.getSortOrder()!=null ? a.getSortOrder() :  Long.valueOf(Long.MIN_VALUE)).compareTo((b.getSortOrder()!=null ? b.getSortOrder() : Long.valueOf(Long.MIN_VALUE))));
        return result;
    }

}
