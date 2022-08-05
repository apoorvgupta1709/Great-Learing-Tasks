package com.cspl.commons.service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Common operations from S3
 * author : Ashutosh
 */

@Slf4j
public class S3Service {
    private final String awsS3AudioBucket;
    private final AmazonS3 amazonS3;

    public S3Service(@NotNull Region awsRegion, @NotNull AWSCredentialsProvider awsCredentialsProvider, @NotNull String awsS3AudioBucket) {
        this.amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).build();
        this.awsS3AudioBucket = awsS3AudioBucket;
    }


    /**
     * Puts a file into s3-bucket and returns s3 URL
     *
     * @param file  file to upload
     * @param folderName  folder name where file will be kept
     * @param enablePublicReadAccess  true if public read access required
     * @return s3 path
     */
    @Async
    public String uploadFile(@NotNull File file, @NotNull String folderName, boolean enablePublicReadAccess) {
        log.error("Entering into [S3Service -> uploadFile], file name {}", file.getName());
        String s3Url = null;
        if (!amazonS3.doesBucketExist(this.awsS3AudioBucket)) {
            amazonS3.createBucket(this.awsS3AudioBucket);
        }

        String filePath = StringUtils.join(folderName, File.separator, file.getName());
        PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3AudioBucket, filePath, file);
        if (enablePublicReadAccess) {
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        }
        try {
            this.amazonS3.putObject(putObjectRequest);
            s3Url = String.valueOf(amazonS3.getUrl(awsS3AudioBucket, filePath));
        } catch (Exception ex) {
            log.error("[S3Service -> uploadFile]Error occurred while putting file to S3, file name {}"
                    , file.getName(), ex);
        }

        return s3Url;
    }

    /**
     * Deletes given file from S3
     *
     * @param filePath - relative file path needed to delete the file
     */
    @Async
    public void delete(@NotNull String filePath) {
        amazonS3.deleteObject(new DeleteObjectRequest(awsS3AudioBucket, filePath));
    }

    /**
     * get file from s3
     *
     * @param path file path to S3
     * @param fileName file name to S3
     */
    public File getFile(@NotNull String path, @NotNull String fileName) {
        S3Object obj = amazonS3.getObject(awsS3AudioBucket, path + File.separator + fileName);
        File s3File = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(s3File)) {
            IOUtils.copy(obj.getObjectContent(), fos);
        } catch (IOException e) {
            log.debug("IOException Occurred while fetching file {}", fileName);
        }
        return s3File;
    }

}
