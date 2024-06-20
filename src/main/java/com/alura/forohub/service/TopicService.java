package com.alura.forohub.service;

import com.alura.forohub.model.Topic;
import com.alura.forohub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic updateTopic(Long id, Topic updatedTopic) {
        return topicRepository.findById(id)
                .map(topic -> {
                    topic.setTitle(updatedTopic.getTitle());
                    topic.setContent(updatedTopic.getContent());
                    topic.setAuthor(updatedTopic.getAuthor());
                    return topicRepository.save(topic);
                })
                .orElseGet(() -> {
                    updatedTopic.setId(id);
                    return topicRepository.save(updatedTopic);
                });
    }

    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}
