package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test

  public void testCommits() throws IOException {
    Github github = new RtGithub("6ee7ead0a281c5b9709b840bbcf0a688f880cc16");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("ezhevikina", "java_pdt56")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}