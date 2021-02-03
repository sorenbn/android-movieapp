package com.sorne.movieapp.di;

import com.sorne.movieapp.services.network.MovieService;
import com.sorne.movieapp.services.network.UserAuthService;
import com.sorne.movieapp.services.repositories.MovieRepository;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class RepositoryModule {
    @Binds
    public abstract MovieRepository bindMovieRepository(MovieService service);

    @Binds
    public abstract UserAuthRepository bindUserAuthRepository(UserAuthService service);
}
