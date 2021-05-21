package com.ipldashboard.data;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.ipldashboard.model.MatchEntity;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MatchData> reader() {
        FlatFileItemReader itemReader = new FlatFileItemReaderBuilder<MatchData>()
                .name("matchItemReader")
                .resource(new ClassPathResource("match-data.csv"))
                .delimited()
                .names(new String[]{"id","city","date","playerOfMatch","venue","neutralVenue","team1","team2","tossWinner","tossDecision","winner","result","resultMargin","eliminator","method","umpire1","umpire2"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchData>() {{
                    setTargetType(MatchData.class);
                }})
                .build();
        itemReader.setLinesToSkip(1);
        return itemReader;

    }

    @Bean
    public MatchProcessor processor() {
        return new MatchProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<MatchEntity> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<MatchEntity>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match_entity (id, city, date, player_of_match, venue, neutral_venue, team1, team2, toss_winner, toss_decision, winner, result, result_margin, eliminator, method, umpire1, umpire2)" +
                        " VALUES (:id, :city, :date, :playerOfMatch, :venue, :neutralVenue, :team1, :team2, :tossWinner, :tossDecision, :winner, :result, :resultMargin, :eliminator, :method, :umpire1, :umpire2)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<MatchEntity> writer) {
        return stepBuilderFactory.get("step1")
                .<MatchData, MatchEntity> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
