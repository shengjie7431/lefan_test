package com.lefancrm.apicenter.tree.impl;

import com.lefancrm.apicenter.dto.TreeData;
import com.lefancrm.apicenter.model.CommonArea;
import com.lefancrm.apicenter.tree.IBaseTree;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/6/20.
 */
@Service
public class AreaTreeImpl implements IBaseTree<CommonArea>{

    @Override
    public List<CommonArea> _createTree(List<CommonArea> _results) {
        if (CollectionUtils.isEmpty(_results)) {
            return null;
        }
        List<CommonArea> treeList = new ArrayList<CommonArea>();
        int len = _results.size();
        for (int i = 0; i < len; i++) {
            CommonArea menu = _results.get(i);
            if (!treeList.contains(menu)) {
                treeList.add(menu);
            }
            List<CommonArea> childList = _getChild(_results, menu.getAreaId());
            if (childList.size() > 0) {
                if (!treeList.containsAll(childList)) {
                    for (CommonArea child : childList) {
                        treeList.add(child);
                        childList = _getChild(_results, child.getAreaId());
                        if (!treeList.containsAll(childList)) {
                            treeList.addAll(childList);
                        }
                    }
                }
            }
        }
        return treeList;
    }

    @Override
    public List<CommonArea> _getChild(List<CommonArea> _results, Long parentId) {
        List<CommonArea> childList = new ArrayList<CommonArea>();
        for (CommonArea m : _results) {
            if (m.getParentId() == parentId) {
                childList.add(m);
            }
        }
        return childList;
    }

    @Override
    public List<TreeData> _resultTreeDate(List<CommonArea> treeDates, Object... objs) {
        List<CommonArea> _createTree = _createTree(treeDates);
        List<TreeData> _results = new ArrayList();
        if (CollectionUtils.isNotEmpty(_createTree)) {
            for (CommonArea menu : _createTree) {
                TreeData tree = new TreeData();
                tree.setId(menu.getAreaId());
                tree.setName(menu.getAreaName());
                tree.setpId(menu.getParentId());
                if (menu.getParentId() != null && menu.getParentId() == -1) {
                    tree.setOpen(true);
                }
                _results.add(tree);
            }
        }
        return _results;
    }
}
