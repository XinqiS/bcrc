package com.sooka.component.lucene;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sooka.component.lucene.util.DocumentUtil;
import com.sooka.component.lucene.util.IndexObject;
import com.sooka.component.lucene.util.QueryUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * Description:lucene
 *
 *
 * @create 2017-05-18
 **/
public class LuceneManager {

	private final static Logger log = LoggerFactory.getLogger(LuceneManager.class);
	private String indexDir = "F:/upload_file_root/index";// 索引目录

	private  Directory directory = null;
	private  Analyzer analyzer = null;
	private String indexDer = null;

	public void setIndexDer(String indexDer) {
		this.indexDer = indexDer;
	}

	public Analyzer getAnalyzer() {
		if(analyzer == null){
	         analyzer = new StandardAnalyzer();
		}
		return analyzer;
	}

	public Directory getDirectory() throws IOException {
		if(directory == null){
			File indexRepositoryFile = new File(this.indexDir);
	        Path path = indexRepositoryFile.toPath();
//	        directory = FSDirectory.open(path);//索引放入本地磁盘
			directory = new RAMDirectory();//索引放入内存
		}
		return directory;
	}


	/*创建索引*/
    public void create(IndexObject indexObject) {
		IndexWriter indexWriter = null;
		try {
			IndexWriterConfig config = new IndexWriterConfig(this.getAnalyzer());
			indexWriter = new IndexWriter(this.getDirectory(), config);
			indexWriter.addDocument(DocumentUtil.IndexObject2Document(indexObject));
			indexWriter.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 if(indexWriter!=null){
				 try {
					 indexWriter.rollback();
				 } catch (IOException e1) {
					 e1.printStackTrace();
				 }
			 }
		 }finally {
			if(indexWriter!=null){
				try {
					indexWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

    }


	/* 删除索引 */
	public void delete(IndexObject indexObject) {
		IndexWriter indexWriter = null;
		try {
			IndexWriterConfig config = new IndexWriterConfig(this.getAnalyzer());
			indexWriter = new IndexWriter(this.getDirectory(), config);
			Long result =indexWriter.deleteDocuments(new Term("id", indexObject.getId()));
			log.info("deleted:{}",result);
		} catch (Exception e) {
			e.printStackTrace();
			if(indexWriter!=null){
				try {
					indexWriter.rollback();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if(indexWriter!=null){
				try {
					indexWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

    /* 删除索引 */
	public void deleteAll() {
		IndexWriter indexWriter = null;
		try {
			IndexWriterConfig config = new IndexWriterConfig(this.getAnalyzer());
			indexWriter = new IndexWriter(this.getDirectory(), config);
			Long result =indexWriter.deleteAll();
			/*清空回收站*/
			indexWriter.forceMergeDeletes();
           log.info("deleted:{}",result);
		} catch (Exception e) {
			e.printStackTrace();
			if(indexWriter!=null){
				try {
					indexWriter.rollback();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if(indexWriter!=null){
				try {
					indexWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/* 更新单挑索引 */
	public void update(IndexObject indexObject) {

		IndexWriter indexWriter = null;

		try {
			IndexWriterConfig config = new IndexWriterConfig(this.getAnalyzer());
			indexWriter = new IndexWriter(this.getDirectory(), config);
			indexWriter.updateDocument(new Term("id", indexObject.getId()),DocumentUtil.IndexObject2Document(indexObject));
		} catch (Exception e) {
			e.printStackTrace();
			if(indexWriter!=null){
				try {
					indexWriter.rollback();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if(indexWriter!=null){
				try {
					indexWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/* 查询索引 */
    public PageInfo<IndexObject> page(Integer pageNumber, Integer pageSize,String title){

		IndexReader indexReader = null;
		PageInfo<IndexObject> pageQuery = null;
		List<IndexObject> searchResults = Lists.newArrayList();
		try {
			indexReader = DirectoryReader.open(this.getDirectory());
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			Query query = QueryUtil.query(title,this.getAnalyzer(),"title","keywords","description");
            ScoreDoc lastScoreDoc = this.getLastScoreDoc(pageNumber, pageSize, query, indexSearcher);
            /*将上一页的最后一个document传递给searchAfter方法以得到下一页的结果 */
            TopDocs topDocs = indexSearcher.searchAfter(lastScoreDoc,query, pageSize);
			Highlighter highlighter = this.addStringHighlighter(query);
			log.info("搜索词语：{}",title);
			log.info("总共的查询结果：{}", topDocs.totalHits);
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			    int docID = scoreDoc.doc;
			    float score = scoreDoc.score;
			    Document document = indexSearcher.doc(docID);
			    IndexObject indexObject =DocumentUtil.document2IndexObject(this.getAnalyzer(), highlighter, document,score);
				searchResults.add(indexObject);
			    log.info("相关度得分：" + score);
			}
			pageQuery = new PageInfo<>();
			pageQuery.setPageNum(pageNumber);
			pageQuery.setPageSize(pageSize);
			pageQuery.setTotal(topDocs.totalHits);
			Collections.sort(searchResults);
			pageQuery.setList(searchResults);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(indexReader!=null){
				try {
					indexReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pageQuery;
	}
    
    /* 根据页码和分页大小获取上一次的最后一个ScoreDoc */
    private ScoreDoc getLastScoreDoc(Integer pageNumber,Integer pageSize,Query query,IndexSearcher searcher) throws IOException {
        if(pageNumber==1) {
            return null;
        }
        int total = pageSize*(pageNumber-1);
        TopDocs topDocs = searcher.search(query,total);
        return topDocs.scoreDocs[total -1];
    } 
    
    
	/* 设置字符串高亮 */
	private Highlighter addStringHighlighter(Query query){
        QueryScorer scorer=new QueryScorer(query);
        Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<font color='red'>","</font>");
		Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
		highlighter.setTextFragmenter(fragmenter); 
		return highlighter;
	}


}  
