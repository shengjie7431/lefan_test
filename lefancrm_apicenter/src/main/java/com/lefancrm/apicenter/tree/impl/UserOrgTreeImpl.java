package com.lefancrm.apicenter.tree.impl;

import com.lefancrm.apicenter.dto.TreeData;
import com.lefancrm.apicenter.model.OrgInfo;
import com.lefancrm.apicenter.tree.IBaseTree;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/6/20.
 */
@Service
public class UserOrgTreeImpl implements IBaseTree<OrgInfo>{

    @Override
    public List<OrgInfo> _createTree(List<OrgInfo> _results) {
        if (CollectionUtils.isEmpty(_results)) {
            return null;
        }
        List<OrgInfo> treeList = new ArrayList<OrgInfo>();
        int len = _results.size();
        for (int i = 0; i < len; i++) {
            OrgInfo menu = _results.get(i);
            if (!treeList.contains(menu)) {
                treeList.add(menu);
            }
            List<OrgInfo> childList = _getChild(_results, menu.getId());
            if (childList.size() > 0) {
                if (!treeList.containsAll(childList)) {
                    for (OrgInfo child : childList) {
                        treeList.add(child);
                        childList = _getChild(_results, child.getId());
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
    public List<OrgInfo> _getChild(List<OrgInfo> _results, Long parentId) {
        List<OrgInfo> childList = new ArrayList<OrgInfo>();
        for (OrgInfo m : _results) {
            if (m.getOrgParentid() == parentId) {
                childList.add(m);
            }
        }
        return childList;
    }

    @Override
    public List<TreeData> _resultTreeDate(List<OrgInfo> treeDates, Object... objs) {
        List<OrgInfo> _createTree = _createTree(treeDates);
        List<TreeData> _results = new ArrayList();
        if (CollectionUtils.isNotEmpty(_createTree)) {
            for (OrgInfo menu : _createTree) {
                TreeData tree = new TreeData();
                tree.setId(menu.getId());
                tree.setName(menu.getOrgName());
                tree.setpId(menu.getOrgParentid());
                if (menu.getOrgParentid() != null && menu.getOrgParentid() == -1) {
                    tree.setOpen(true);
                }
                _results.add(tree);
            }
        }
        return _results;
    }
}
