package com.matariky.commonservice.minio.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matariky.commonservice.minio.domain.Fileinfo;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

@Component
public class MinioUtil {
	@Autowired
	private MinioClient minioClient;

	/**
	 * Create A Bucket
	 */
	public void createBucket(String bucket) throws Exception {
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
		if (!found) {
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
		}
	}

	/**
	 * Upload a file
	 */
	public ObjectWriteResponse uploadFile(InputStream stream, String bucket, String objectName) throws Exception {
		return minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(objectName)
				.stream(stream, -1, 10485760).build());
	}

	/**
	 * List all buckets
	 */
	public List<String> listBuckets() throws Exception {
		List<Bucket> list = minioClient.listBuckets();
		List<String> names = new ArrayList<>();
		list.forEach(b -> {
			names.add(b.name());
		});
		return names;
	}

	/**
	 * List all files and directory in A bucket
	 */
	public List<Fileinfo> listFiles(String bucket) throws Exception {
		Iterable<Result<Item>> results = minioClient.listObjects(
				ListObjectsArgs.builder().bucket(bucket).recursive(true).build());

		List<Fileinfo> infos = new ArrayList<>();
		results.forEach(r -> {
			Fileinfo info = new Fileinfo();
			try {
				Item item = r.get();
				info.setFilename(item.objectName());
				info.setDirectory(item.isDir());
				infos.add(info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return infos;
	}

	/**
	 * Download A file
	 */
	public InputStream download(String bucket, String objectName) throws Exception {
		InputStream stream = minioClient.getObject(
				GetObjectArgs.builder().bucket(bucket).object(objectName).build());
		return stream;
	}

	/**
	 * Delete A Bucket
	 */
	public void deleteBucket(String bucket) throws Exception {
		minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
	}

	/**
	 * Delete one Object
	 */
	public void deleteObject(String bucket, String objectName) throws Exception {
		minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
	}

}
