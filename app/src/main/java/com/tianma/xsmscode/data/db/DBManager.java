package com.tianma.xsmscode.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tianma.xsmscode.data.db.entity.AppInfo;
import com.tianma.xsmscode.data.db.entity.DaoMaster;
import com.tianma.xsmscode.data.db.entity.DaoSession;
import com.tianma.xsmscode.data.db.entity.SmsCodeRule;
import com.tianma.xsmscode.data.db.entity.SmsCodeRuleDao;
import com.tianma.xsmscode.data.db.entity.SmsMsg;
import com.tianma.xsmscode.data.db.entity.SmsMsgDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import io.reactivex.Observable;

/**
 * Database Manager for GreenDao
 */
public class DBManager {

    private static final String DB_NAME = "sms-code.db";

    private static DBManager sInstance;

    private DaoSession mDaoSession;
    private SQLiteDatabase mSQLiteDatabase;

    private DBManager(Context context) {
        TSQLiteOpenHelper dbOpenHelper =
                new TSQLiteOpenHelper(context.getApplicationContext(), DB_NAME);
        mSQLiteDatabase = dbOpenHelper.getWritableDatabase();
        mDaoSession = new DaoMaster(mSQLiteDatabase).newSession();
    }

    public static DBManager get(Context context) {
        if (sInstance == null) {
            synchronized (DBManager.class) {
                if (sInstance == null) {
                    sInstance = new DBManager(context);
                }
            }
        }
        return sInstance;
    }

    SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }

    @SuppressWarnings("unchecked")
    private <T> AbstractDao<T, ?> getAbstractDao(Class<T> entityClass) {
        return (AbstractDao<T, ?>) mDaoSession.getDao(entityClass);
    }

    public <T> long insertOrReplace(Class<T> entityClass, T entity) {
        AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
        return abstractDao.insertOrReplace(entity);
    }

    public <T> Observable<T> insertOrReplaceRx(Class<T> entityClass, T entity) {
        return Observable.fromCallable(() -> {
            AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
            abstractDao.insertOrReplace(entity);
            return entity;
        });
    }

    public <T> void insertOrReplaceInTx(Class<T> entityClass, List<T> entities) {
        AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
        abstractDao.insertOrReplaceInTx(entities);
    }

    public <T> Observable<List<T>> insertOrReplaceInTxRx(Class<T> entityClass, List<T> entities) {
        return Observable.fromCallable(() -> {
            AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
            abstractDao.insertOrReplaceInTx(entities);
            return entities;
        });
    }

    private <T> void update(Class<T> entityClass, T entity) {
        AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
        abstractDao.update(entity);
    }

    public <T> Observable<T> updateRx(Class<T> entityClass, T entity) {
        return Observable.fromCallable(() -> {
            AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
            abstractDao.update(entity);
            return entity;
        });
    }

    private <T> void delete(Class<T> entityClass, T entity) {
        AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
        abstractDao.delete(entity);
    }

    public <T> Observable<T> deleteRx(Class<T> entityClass, T entity) {
        return Observable.fromCallable(() -> {
            AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
            abstractDao.delete(entity);
            return entity;
        });
    }

    private <T> void deleteInTx(Class<T> entityClass, List<T> entities) {
        AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
        abstractDao.deleteInTx(entities);
    }

    public <T> Observable<List<T>> deleteInTxRx(Class<T> entityClass, List<T> entities) {
        return Observable.fromCallable(() -> {
            AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
            abstractDao.deleteInTx(entities);
            return entities;
        });
    }

    public <T> void deleteAll(Class<T> entityClass) {
        AbstractDao abstractDao = getAbstractDao(entityClass);
        abstractDao.deleteAll();
    }

    public <T> Observable<Boolean> deleteAllRx(Class<T> entityClass) {
        return Observable.fromCallable(() -> {
            AbstractDao<T, ?> abstractDao = getAbstractDao(entityClass);
            abstractDao.deleteAll();
            return true;
        });
    }

    private <T> QueryBuilder<T> getQueryBuilder(Class<T> entityClass) {
        return mDaoSession.queryBuilder(entityClass);
    }

    public <T> List<T> queryAll(Class<T> entityClass) {
        return getQueryBuilder(entityClass).list();
    }

    public <T> Observable<List<T>> queryAllRx(Class<T> entityClass) {
        return Observable.fromCallable(() -> {
            QueryBuilder<T> queryBuilder = getQueryBuilder(entityClass);
            return queryBuilder.list();
        });
    }

    public long addSmsCodeRule(SmsCodeRule smsCodeRule) {
        return insertOrReplace(SmsCodeRule.class, smsCodeRule);
    }

    public Observable<SmsCodeRule> addSmsCodeRuleRx(SmsCodeRule smsCodeRule) {
        return insertOrReplaceRx(SmsCodeRule.class, smsCodeRule);
    }

    public void addSmsCodeRules(List<SmsCodeRule> smsCodeRules) {
        insertOrReplaceInTx(SmsCodeRule.class, smsCodeRules);
    }

    public Observable<List<SmsCodeRule>> addSmsCodeRulesRx(List<SmsCodeRule> smsCodeRules) {
        return insertOrReplaceInTxRx(SmsCodeRule.class, smsCodeRules);
    }

    public void updateSmsCodeRule(SmsCodeRule smsCodeRule) {
        update(SmsCodeRule.class, smsCodeRule);
    }

    public Observable<SmsCodeRule> updateSmsCodeRuleRx(SmsCodeRule smsCodeRule) {
        return updateRx(SmsCodeRule.class, smsCodeRule);
    }

    public List<SmsCodeRule> queryAllSmsCodeRules() {
        return queryAll(SmsCodeRule.class);
    }

    public Observable<List<SmsCodeRule>> queryAllSmsCodeRulesRx() {
        return Observable.fromCallable(() -> queryAll(SmsCodeRule.class));
    }

    public List<SmsCodeRule> querySmsCodeRules(SmsCodeRule criteria) {
        SmsCodeRuleDao dao = mDaoSession.getSmsCodeRuleDao();
        return dao.queryBuilder().
                where(
                        SmsCodeRuleDao.Properties.Company.eq(criteria.getCompany()),
                        SmsCodeRuleDao.Properties.CodeKeyword.eq(criteria.getCodeKeyword()),
                        SmsCodeRuleDao.Properties.CodeRegex.eq(criteria.getCodeRegex())
                ).list();
    }

    public Observable<List<SmsCodeRule>> querySmsCodeRulesRx(SmsCodeRule criteria) {
        return Observable.fromCallable(() -> {
            SmsCodeRuleDao dao = mDaoSession.getSmsCodeRuleDao();
            return dao.queryBuilder().
                    where(
                            SmsCodeRuleDao.Properties.Company.eq(criteria.getCompany()),
                            SmsCodeRuleDao.Properties.CodeKeyword.eq(criteria.getCodeKeyword()),
                            SmsCodeRuleDao.Properties.CodeRegex.eq(criteria.getCodeRegex())
                    ).list();
        });
    }

    public boolean isExists(SmsCodeRule codeRule) {
        return !querySmsCodeRules(codeRule).isEmpty();
    }

    public Observable<Boolean> isExistsRx(SmsCodeRule codeRule) {
        return querySmsCodeRulesRx(codeRule).isEmpty().toObservable();
    }

    public void removeSmsCodeRule(SmsCodeRule smsCodeRule) {
        delete(SmsCodeRule.class, smsCodeRule);
    }

    public Observable<SmsCodeRule> removeSmsCodeRuleRx(SmsCodeRule smsCodeRule) {
        return deleteRx(SmsCodeRule.class, smsCodeRule);
    }

    public void removeAllSmsCodeRules() {
        deleteAll(SmsCodeRule.class);
    }

    public Observable<Boolean> removeAllSmsCodeRulesRx() {
        return deleteAllRx(SmsCodeRule.class);
    }

    public void addSmsMsg(SmsMsg smsMsg) {
        insertOrReplace(SmsMsg.class, smsMsg);
    }

    public Observable<SmsMsg> addSmsMsgRx(SmsMsg smsMsg) {
        return insertOrReplaceRx(SmsMsg.class, smsMsg);
    }

    public void addSmsMsgList(List<SmsMsg> smsMsgList) {
        insertOrReplaceInTx(SmsMsg.class, smsMsgList);
    }

    public Observable<List<SmsMsg>> addSmsMsgListRx(List<SmsMsg> smsMsgList) {
        return insertOrReplaceInTxRx(SmsMsg.class, smsMsgList);
    }

    public List<SmsMsg> queryAllSmsMsg() {
        return mDaoSession.queryBuilder(SmsMsg.class)
                .orderDesc(SmsMsgDao.Properties.Date)
                .list();
    }

    public Observable<List<SmsMsg>> queryAllSmsMsgRx() {
        return Observable.fromCallable(() ->
                mDaoSession.queryBuilder(SmsMsg.class)
                        .orderDesc(SmsMsgDao.Properties.Date)
                        .list()
        );
    }

    public void removeSmsMsgList(List<SmsMsg> smsMsgList) {
        deleteInTx(SmsMsg.class, smsMsgList);
    }

    public Observable<List<SmsMsg>> removeSmsMsgListRx(List<SmsMsg> smsMsgList) {
        return deleteInTxRx(SmsMsg.class, smsMsgList);
    }

    public List<AppInfo> queryAllBlockedApps() {
        return queryAll(AppInfo.class);
    }

    public Observable<List<AppInfo>> queryAllBlockedAppsRx() {
        return Observable.fromCallable(() -> mDaoSession.queryBuilder(AppInfo.class).list());
    }

    public Observable<List<AppInfo>> removeBlockedApps(List<AppInfo> appList) {
        return deleteInTxRx(AppInfo.class, appList);
    }

    public Observable<List<AppInfo>> addBlockedApps(List<AppInfo> appList) {
        return insertOrReplaceInTxRx(AppInfo.class, appList);
    }
}
