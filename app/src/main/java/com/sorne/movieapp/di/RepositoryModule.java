package com.sorne.movieapp.di;

import com.sorne.movieapp.network.NetworkMovieRepository;
import com.sorne.movieapp.repositories.MovieRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class RepositoryModule {
    @Binds
    public abstract MovieRepository bindMovieRepository(NetworkMovieRepository repository);
}
