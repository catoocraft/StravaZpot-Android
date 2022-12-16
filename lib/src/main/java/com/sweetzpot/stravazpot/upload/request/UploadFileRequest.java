package com.sweetzpot.stravazpot.upload.request;

import com.sweetzpot.stravazpot.upload.api.UploadAPI;
import com.sweetzpot.stravazpot.upload.model.DataType;
import com.sweetzpot.stravazpot.upload.model.UploadActivityType;
import com.sweetzpot.stravazpot.upload.model.UploadStatus;
import com.sweetzpot.stravazpot.upload.rest.UploadRest;

import java.io.File;
import java.io.IOException;
import java.io.EOFException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import retrofit2.Call;

public class UploadFileRequest {
    public interface ProgressCallback {
         void progressUpdate(float fractionCompleted);
    }

    private final File file;
    private final UploadRest restService;
    private final UploadAPI uploadAPI;
    private UploadActivityType activityType;
    private String name;
    private String description;
    private boolean isPrivate;
    private boolean hasTrainer;
    private boolean isCommute;
    private DataType dataType;
    private String externalID;
    private ProgressCallback progressCallback;

    public UploadFileRequest(File file, UploadRest restService, UploadAPI uploadAPI) {
        this.file = file;
        this.restService = restService;
        this.uploadAPI = uploadAPI;
    }

    public UploadFileRequest withDataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public UploadFileRequest withActivityType(UploadActivityType activityType) {
        this.activityType = activityType;
        return this;
    }

    public UploadFileRequest withName(String name) {
        this.name = name;
        return this;
    }

    public UploadFileRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public UploadFileRequest isPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }

    public UploadFileRequest hasTrainer(boolean hasTrainer) {
        this.hasTrainer = hasTrainer;
        return this;
    }

    public UploadFileRequest isCommute(boolean isCommute) {
        this.isCommute = isCommute;
        return this;
    }

    public UploadFileRequest withExternalID(String externalID) {
        this.externalID = externalID;
        return this;
    }

    public UploadFileRequest withProgressCallback(ProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
        return this;
    }

    public UploadStatus execute() {
        RequestBody requestFile = requestBodyFromFile(MediaType.parse("multipart/form-data"), file, progressCallback);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Call<UploadStatus> call = restService.upload(
                requestBodyFromString(activityType.toString()),
                requestBodyFromString(name),
                requestBodyFromString(description),
                booleanToInt(isPrivate),
                booleanToInt(hasTrainer),
                booleanToInt(isCommute),
                requestBodyFromString(dataType.toString()),
                requestBodyFromString(externalID),
                body);
        return uploadAPI.execute(call);
    }

    private RequestBody requestBodyFromString(String str) {
        if (str == null) {
            return null;
        }

        return RequestBody.create(MultipartBody.FORM, str);
    }

    private Integer booleanToInt(boolean b) {
        return b ? 1 : 0;
    }

    // This is an inlined copy of okhttp3.RequestBody.create() in order to provide a simpple upload progress callback mechanism
    private RequestBody requestBodyFromFile(final MediaType contentType, final File file, final ProgressCallback progressCallback) {
        if (file == null) throw new NullPointerException("content == null");

        return new RequestBody() {
             @Override public MediaType contentType() {
                 return contentType;
             }

             @Override public long contentLength() {
                 return file.length();
             }

             @Override public void writeTo(BufferedSink sink) throws IOException {
                 Source source = null;
                 try {
                     source = Okio.source(file);
                     float progressCoefficient = 1.0f / file.length();
                     float bytesWritten = 0;
                     while (true) {
                         sink.write(source, 8192);
                         bytesWritten += 8192;
                         if (progressCallback != null) {
                             progressCallback.progressUpdate(bytesWritten * progressCoefficient);
                         }
                     }
                 } catch (EOFException e) {
                     // write() signals it's got to end of file by throwing an exception :eyeroll:
                 } finally {
                     Util.closeQuietly(source);
                 }
             }
         };
    }
}
