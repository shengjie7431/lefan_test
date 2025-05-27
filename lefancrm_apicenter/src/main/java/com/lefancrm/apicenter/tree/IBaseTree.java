package com.lefancrm.apicenter.tree;



import com.lefancrm.apicenter.dto.TreeData;

import java.util.List;

/**
 * Created by DELL on 2017/6/20.
 */
public interface IBaseTree<T> {
    List<T> _createTree(List<T> _results);
    List<T> _getChild(List<T> _results, Long parentId);
    public List<TreeData> _resultTreeDate(List<T> treeDates, Object... objs);
}
