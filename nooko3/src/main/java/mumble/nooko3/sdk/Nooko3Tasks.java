package mumble.nooko3.sdk;

import android.content.Context;

import java.util.ArrayList;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getBlock;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getBlocks;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getProject;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getSection;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getSections;
import mumble.nooko3.sdk.NConstants.NUserConst;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiBlockResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiBlocksResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiProjectResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiSectionResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiSectionsResultListener;
import mumble.nooko3.sdk.NExceptions.NSDKInitializeException;

public class Nooko3Tasks {

    /**Asks project data from API*/
    public static void askForProject(Context context){
        if(NUserConst.apiKey != null) {
            new ATask_getProject(context).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks project data from API with custom action return*/
    public static void askForProject(Context context, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getProject(context, custom_action).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks project data from API with listener*/
    public static void askForProject(Context context, NApiProjectResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getProject(context, listener).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections with a custom action return*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, custom_action, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections with listener*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, NApiBlocksResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, listener, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements with a custom action return*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, custom_action, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements with listener*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, NApiBlocksResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, listener, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks sections data from API with or without elements*/
    public static void askForSections(Context context, ArrayList<Object> filters, boolean getElements){
        if(NUserConst.apiKey != null) {
            new ATask_getSections(context, filters, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks sections data from API with or without elements with a custom action return*/
    public static void askForSections(Context context, ArrayList<Object> filters, boolean getElements, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getSections(context, filters, custom_action, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks sections data from API with or without elements with listener*/
    public static void askForSections(Context context, ArrayList<Object> filters, boolean getElements, NApiSectionsResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getSections(context, filters, listener, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks section data from API with or without elements with listener*/
    public static void askForSection(Context context, long section_id, boolean getElements){
        if(NUserConst.apiKey != null) {
            new ATask_getSection(context, section_id, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }


    /**Asks section data from API with or without elements with custom action*/
    public static void askForSection(Context context, long section_id, boolean getElements, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getSection(context, section_id, custom_action, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks section data from API with or without elements with listener*/
    public static void askForSection(Context context, long section_id, boolean getElements, NApiSectionResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getSection(context, section_id, listener, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections*/
    public static void askForBlock(Context context, long block_id, boolean getSections){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements*/
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections with a custom action return*/
    public static void askForBlock(Context context, long block_id, boolean getSections, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, custom_action, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections with listener*/
    public static void askForBlock(Context context, long block_id, boolean getSections, NApiBlockResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, listener, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements with a custom action return*/
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, custom_action, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements with listener*/
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements, NApiBlockResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, listener, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
