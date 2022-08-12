package com.tianma.xsmscode.ui.rule.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.tianma8023.xposed.smscode.R;
import com.tianma.xsmscode.common.adapter.ItemCallback;
import com.tianma.xsmscode.data.db.entity.SmsCodeRule;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.VH> {

    private Context mContext;
    private List<SmsCodeRule> mDataList;
    private ItemCallback<SmsCodeRule> mItemCallback;

    RuleAdapter(Context context, List<SmsCodeRule> ruleList) {
        mContext = context;
        mDataList = ruleList;
    }

    public void setItemCallback(ItemCallback<SmsCodeRule> itemCallback) {
        mItemCallback = itemCallback;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_rule, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        SmsCodeRule item = mDataList.get(position);
        holder.bindData(item);
        holder.bindListener(item, position);
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView mCompanyView;
        TextView mKeywordView;
        TextView mRegexView;

        VH(View itemView) {
            super(itemView);
            mCompanyView = itemView.findViewById(R.id.rule_company_text_view);
            mKeywordView = itemView.findViewById(R.id.rule_keyword_text_view);
            mRegexView = itemView.findViewById(R.id.rule_regex_text_view);
        }

        void bindData(SmsCodeRule item) {
            mCompanyView.setText(item.getCompany());
            mKeywordView.setText(item.getCodeKeyword());
            mRegexView.setText(item.getCodeRegex());
        }

        void bindListener(final SmsCodeRule item, final int position) {
            if (mItemCallback != null) {
                itemView.setOnClickListener(v ->
                        mItemCallback.onItemClicked(itemView, item, position));

                itemView.setOnLongClickListener(v ->
                        mItemCallback.onItemLongClicked(itemView, item, position));

                itemView.setOnCreateContextMenuListener((menu, v, menuInfo) ->
                        mItemCallback.onCreateItemContextMenu(menu, v, menuInfo, item, position));
            }
        }
    }

    public void addRule(SmsCodeRule newRule) {
        if (!mDataList.contains(newRule)) {
            mDataList.add(newRule);
            notifyDataSetChanged();
        }
    }

    public void addRule(List<SmsCodeRule> ruleList) {
        mDataList.addAll(ruleList);
        notifyDataSetChanged();
    }

    public void addRule(int position, SmsCodeRule newRule) {
        if (!mDataList.contains(newRule)) {
            mDataList.add(position, newRule);
            notifyDataSetChanged();
        }
    }

    public void updateAt(int position, SmsCodeRule updatedRule) {
        SmsCodeRule item = getItemAt(position);
        if (item != null) {
            item.copyFrom(updatedRule);
            notifyDataSetChanged();
        }
    }

    public SmsCodeRule getItemAt(int position) {
        if (position < 0 || position > getItemCount()) {
            return null;
        }
        return mDataList.get(position);
    }

    public void removeItemAt(int position) {
        SmsCodeRule item = getItemAt(position);
        if (item != null) {
            mDataList.remove(item);
            notifyDataSetChanged();
        }
    }

    public List<SmsCodeRule> getRuleList() {
        return mDataList;
    }

    public void setRules(List<SmsCodeRule> ruleList) {
        if (ruleList != null) {
            mDataList.clear();
            mDataList.addAll(ruleList);
            notifyDataSetChanged();
        }
    }
}
